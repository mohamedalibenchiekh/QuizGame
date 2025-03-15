package com.quizgame.gameplay;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.quizgame.proto.QuizRequest;
import com.quizgame.proto.QuizResponse;
import com.quizgame.proto.AnswerRequest;
import com.quizgame.proto.AnswerResponse;
import com.quizgame.proto.GameplayGrpc;

public class GameplayClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        GameplayGrpc.GameplayBlockingStub stub = GameplayGrpc.newBlockingStub(channel);

        // Load quiz questions
        QuizRequest quizRequest = QuizRequest.newBuilder().build();
        QuizResponse quizResponse = stub.loadQuiz(quizRequest);
        System.out.println("Quiz questions: " + quizResponse.getQuestionsList());

        // Submit an answer
        AnswerRequest answerRequest = AnswerRequest.newBuilder()
                .setPlayerName("JohnDoe")
                .setAnswer("Paris")
                .build();

        AnswerResponse answerResponse = stub.submitAnswer(answerRequest);
        System.out.println("Answer response: " + answerResponse.getMessage());

        channel.shutdown();
    }
}