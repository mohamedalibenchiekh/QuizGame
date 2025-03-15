package com.quizgame.gameplay;

import io.grpc.stub.StreamObserver;
import com.quizgame.proto.QuizRequest;
import com.quizgame.proto.QuizResponse;
import com.quizgame.proto.AnswerRequest;
import com.quizgame.proto.AnswerResponse;
import com.quizgame.proto.GameplayGrpc;

public class GameplayService extends GameplayGrpc.GameplayImplBase {

    @Override
    public void loadQuiz(QuizRequest request, StreamObserver<QuizResponse> responseObserver) {
        // Simulate loading quiz questions from the database
        QuizResponse response = QuizResponse.newBuilder()
                .addQuestions("What is the capital of France?")
                .addQuestions("What is 2 + 2?")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void submitAnswer(AnswerRequest request, StreamObserver<AnswerResponse> responseObserver) {
        String playerName = request.getPlayerName();
        String answer = request.getAnswer();

        // Simulate answer validation
        boolean isCorrect = answer.equals("Paris"); // Example validation

        AnswerResponse response = AnswerResponse.newBuilder()
                .setMessage(isCorrect ? "Correct answer!" : "Incorrect answer!")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}