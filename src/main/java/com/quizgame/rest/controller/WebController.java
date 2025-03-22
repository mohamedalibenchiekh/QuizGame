package com.quizgame.rest.controller;

import com.quizgame.model.Question;
import com.quizgame.model.Quiz;
import com.quizgame.rest.dto.AnswerDto;
import com.quizgame.rest.dto.PlayerDto;
import com.quizgame.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.bson.types.ObjectId;

import java.util.*;
import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    private final PlayerController playerController;
    private final GameController gameController;
    private final QuizService quizService;
    
    private Map<String, List<String>> playerResponses = new HashMap<>();

    @Autowired
    public WebController(PlayerController playerController, 
                         GameController gameController,
                         QuizService quizService) {
        this.playerController = playerController;
        this.gameController = gameController;
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 1. Login for existing players
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("player", new PlayerDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginPlayer(@ModelAttribute PlayerDto player, 
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        // For admin login (hardcoded)
        if ("admin".equals(player.getUsername()) && "test".equals(player.getPassword())) {
            // Store admin username in session
            session.setAttribute("username", player.getUsername());
            session.setAttribute("isAdmin", true);
            return "redirect:/admin";
        }

        // For regular player login (in a real app, use authentication service)
        try {
            // TODO: Implement proper authentication with the gRPC service
            // For now, we'll assume authentication succeeds if username and password are not empty
            if (player.getUsername() != null && !player.getUsername().isEmpty() && 
                player.getPassword() != null && !player.getPassword().isEmpty()) {
                // Store username in session
                session.setAttribute("username", player.getUsername());
                session.setAttribute("isAdmin", false);
                redirectAttributes.addFlashAttribute("message", "Login successful!");
                redirectAttributes.addFlashAttribute("playerName", player.getUsername());
                return "redirect:/quizzes";
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid username or password");
                return "redirect:/login";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Login failed: " + e.getMessage());
            return "redirect:/login";
        }
    }

    // 2. Register a new player
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("player", new PlayerDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerPlayer(@ModelAttribute PlayerDto player, 
                                RedirectAttributes redirectAttributes) {
        try {
            var response = playerController.registerPlayer(player);
            if (response.isSuccess()) {
                redirectAttributes.addFlashAttribute("message", "Registration successful: " + response.getData());
                redirectAttributes.addFlashAttribute("playerName", player.getUsername());
                return "redirect:/quizzes";
            } else {
                redirectAttributes.addFlashAttribute("error", response.getMessage());
                return "redirect:/register";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }

    // 3. Admin dashboard and functionality
    @GetMapping("/admin")
    public String adminDashboard(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Check if user is admin
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            redirectAttributes.addFlashAttribute("error", "You must be an admin to access this page");
            return "redirect:/login";
        }
        
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "admin";
    }
    
    @GetMapping("/admin/questions")
    public String adminQuestions(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Check if user is admin
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            redirectAttributes.addFlashAttribute("error", "You must be an admin to access this page");
            return "redirect:/login";
        }
        
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        
        // Get all quizzes for the dropdown
        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        
        return "admin-questions";
    }
    
    @PostMapping("/admin/questions/add")
    public String addQuestion(HttpSession session,
                             @RequestParam(required = false) String quizId,
                             @RequestParam(required = false) String quizTitle,
                             @RequestParam(required = false) String quizDescription,
                             @RequestParam String questionText,
                             @RequestParam Map<String, String> options,
                             @RequestParam int correctAnswerIndex,
                             RedirectAttributes redirectAttributes) {
        
        // Check if user is admin
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            redirectAttributes.addFlashAttribute("error", "You must be an admin to access this page");
            return "redirect:/login";
        }
        
        try {
            Quiz targetQuiz;
            
            // Check if we're creating a new quiz or using an existing one
            if ("new".equals(quizId)) {
                // Create new quiz
                targetQuiz = quizService.createQuiz(quizTitle, quizDescription);
            } else {
                // Get existing quiz
                targetQuiz = quizService.getQuizById(quizId);
            }
            
            // Extract options from form parameters
            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                optionsList.add(options.get("options[" + i + "]"));
            }
            
            // Create new question
            Question question = new Question(questionText, optionsList, correctAnswerIndex);
            
            // Add to quiz and save
            quizService.addQuestionToQuiz(new ObjectId(targetQuiz.getId().toString()), question);
            
            redirectAttributes.addFlashAttribute("message", "Question added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add question: " + e.getMessage());
        }
        
        return "redirect:/admin/questions";
    }

    // 4. Load quiz questions
    @GetMapping("/quizzes")
    public String listQuizzes(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "quizzes";
    }

    @GetMapping("/quiz/{id}")
    public String loadQuiz(@PathVariable String id, Model model) {
        // Check if this is the featured quiz
        if ("featured".equals(id)) {
            return loadFeaturedQuiz(model);
        }
        
        try {
            Quiz quiz = quizService.getQuizById(id);
            model.addAttribute("quiz", quiz);
            return "quiz";
        } catch (Exception e) {
            return "redirect:/quizzes";
        }
    }
    
    private String loadFeaturedQuiz(Model model) {
        // Try to get a quiz from the database first
        List<Quiz> quizzes = quizService.getAllQuizzes();
        if (quizzes != null && !quizzes.isEmpty()) {
            // Use the first quiz as featured
            model.addAttribute("quiz", quizzes.get(0));
        } else {
            // Create a sample quiz
            Quiz featuredQuiz = createSampleQuiz();
            model.addAttribute("quiz", featuredQuiz);
        }
        return "quiz";
    }
    
    private Quiz createSampleQuiz() {
        Quiz quiz = new Quiz("Geography Quiz", "Test your knowledge of world geography");
        
        Question q1 = new Question(
            "What is the capital of France?",
            Arrays.asList("Paris", "London", "Berlin", "Madrid"),
            0
        );
        
        Question q2 = new Question(
            "Which planet is known as the Red Planet?",
            Arrays.asList("Venus", "Mars", "Jupiter", "Mercury"),
            1
        );
        
        Question q3 = new Question(
            "What is the largest ocean on Earth?",
            Arrays.asList("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
            3
        );
        
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        
        // Set a fake ID since this quiz won't be in the database
        quiz.setId(new ObjectId());
        
        return quiz;
    }

    // 5. Submit answers
    @PostMapping("/quiz/{id}/submit")
    public String submitAnswer(@PathVariable String id, 
                             @RequestParam String playerName,
                             @RequestParam Map<String, String> answers,
                             RedirectAttributes redirectAttributes) {
        
        // Process answers
        List<String> responses = new ArrayList<>();
        
        for (Map.Entry<String, String> entry : answers.entrySet()) {
            if (entry.getKey().startsWith("question_")) {
                String questionId = entry.getKey().substring(9); // Remove "question_" prefix
                String answer = entry.getValue();
                
                // In a real app, you'd validate these answers against the correct ones
                // and store the results
                
                // Mock response for demo
                String response = "Question " + questionId + ": Your answer was " + answer;
                responses.add(response);
            }
        }
        
        // Store responses for this player
        playerResponses.put(playerName, responses);
        
        redirectAttributes.addFlashAttribute("message", "Answers submitted successfully");
        redirectAttributes.addFlashAttribute("playerName", playerName);
        
        return "redirect:/results?player=" + playerName;
    }

    // 6. View results
    @GetMapping("/results")
    public String viewResults(@RequestParam String player, Model model) {
        List<String> responses = playerResponses.getOrDefault(player, new ArrayList<>());
        model.addAttribute("responses", responses);
        model.addAttribute("playerName", player);
        return "results";
    }
    
    // 7. Logout
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "You have been logged out");
        return "redirect:/";
    }
} 