package com.quizgame.player;

import com.quizgame.proto.RegisterPlayerRequest;
import com.quizgame.proto.RegisterPlayerResponse;
import io.grpc.stub.StreamObserver;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PlayerManagementServiceTest {
    private PlayerManagementService playerManagementService;
    private StreamObserver<RegisterPlayerResponse> responseObserver;

    @Before
    public void setUp() {
        playerManagementService = new PlayerManagementService();
        responseObserver = mock(StreamObserver.class);
    }

    @Test
    public void testRegisterPlayer() {
        RegisterPlayerRequest request = RegisterPlayerRequest.newBuilder()
                .setUsername("JohnDoe")
                .setPassword("password123")
                .build();

        playerManagementService.registerPlayer(request, responseObserver);

        verify(responseObserver).onNext(any(RegisterPlayerResponse.class));
        verify(responseObserver).onCompleted();
    }
}