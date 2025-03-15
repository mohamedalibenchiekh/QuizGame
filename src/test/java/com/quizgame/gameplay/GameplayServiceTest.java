package com.quizgame.gameplay;

import com.quizgame.proto.QuizRequest;
import com.quizgame.proto.QuizResponse;
import com.quizgame.proto.AnswerRequest;
import com.quizgame.proto.AnswerResponse;
import io.grpc.stub.StreamObserver;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class GameplayServiceTest {
    private GameplayService gameplayService;
    private StreamObserver<QuizResponse> quizResponseObserver;
    private StreamObserver<AnswerResponse> answerResponseObserver;

    @Before
    public void setUp() {
        gameplayService = new GameplayService();
        quizResponseObserver = mock(StreamObserver.class);
        answerResponseObserver = mock(StreamObserver.class);
    }

    @Test
    public void testLoadQuiz() {
        QuizRequest request = QuizRequest.newBuilder().build();
        gameplayService.loadQuiz(request, quizResponseObserver);

        verify(quizResponseObserver).onNext(any(QuizResponse.class));
        verify(quizResponseObserver).onCompleted();
    }

    @Test
    public void testSubmitAnswer() {
        AnswerRequest request = AnswerRequest.newBuilder()
                .setPlayerName("JohnDoe")
                .setAnswer("Paris")
                .build();

        gameplayService.submitAnswer(request, answerResponseObserver);

        verify(answerResponseObserver).onNext(any(AnswerResponse.class));
        verify(answerResponseObserver).onCompleted();
    }
}