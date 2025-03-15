package com.quizgame.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: quizgame.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GameplayGrpc {

  private GameplayGrpc() {}

  public static final String SERVICE_NAME = "quizgame.Gameplay";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.quizgame.proto.QuizRequest,
      com.quizgame.proto.QuizResponse> getLoadQuizMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LoadQuiz",
      requestType = com.quizgame.proto.QuizRequest.class,
      responseType = com.quizgame.proto.QuizResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.quizgame.proto.QuizRequest,
      com.quizgame.proto.QuizResponse> getLoadQuizMethod() {
    io.grpc.MethodDescriptor<com.quizgame.proto.QuizRequest, com.quizgame.proto.QuizResponse> getLoadQuizMethod;
    if ((getLoadQuizMethod = GameplayGrpc.getLoadQuizMethod) == null) {
      synchronized (GameplayGrpc.class) {
        if ((getLoadQuizMethod = GameplayGrpc.getLoadQuizMethod) == null) {
          GameplayGrpc.getLoadQuizMethod = getLoadQuizMethod =
              io.grpc.MethodDescriptor.<com.quizgame.proto.QuizRequest, com.quizgame.proto.QuizResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LoadQuiz"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizgame.proto.QuizRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizgame.proto.QuizResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GameplayMethodDescriptorSupplier("LoadQuiz"))
              .build();
        }
      }
    }
    return getLoadQuizMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.quizgame.proto.AnswerRequest,
      com.quizgame.proto.AnswerResponse> getSubmitAnswerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitAnswer",
      requestType = com.quizgame.proto.AnswerRequest.class,
      responseType = com.quizgame.proto.AnswerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.quizgame.proto.AnswerRequest,
      com.quizgame.proto.AnswerResponse> getSubmitAnswerMethod() {
    io.grpc.MethodDescriptor<com.quizgame.proto.AnswerRequest, com.quizgame.proto.AnswerResponse> getSubmitAnswerMethod;
    if ((getSubmitAnswerMethod = GameplayGrpc.getSubmitAnswerMethod) == null) {
      synchronized (GameplayGrpc.class) {
        if ((getSubmitAnswerMethod = GameplayGrpc.getSubmitAnswerMethod) == null) {
          GameplayGrpc.getSubmitAnswerMethod = getSubmitAnswerMethod =
              io.grpc.MethodDescriptor.<com.quizgame.proto.AnswerRequest, com.quizgame.proto.AnswerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitAnswer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizgame.proto.AnswerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizgame.proto.AnswerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GameplayMethodDescriptorSupplier("SubmitAnswer"))
              .build();
        }
      }
    }
    return getSubmitAnswerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GameplayStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameplayStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameplayStub>() {
        @java.lang.Override
        public GameplayStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameplayStub(channel, callOptions);
        }
      };
    return GameplayStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GameplayBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameplayBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameplayBlockingStub>() {
        @java.lang.Override
        public GameplayBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameplayBlockingStub(channel, callOptions);
        }
      };
    return GameplayBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GameplayFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameplayFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameplayFutureStub>() {
        @java.lang.Override
        public GameplayFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameplayFutureStub(channel, callOptions);
        }
      };
    return GameplayFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GameplayImplBase implements io.grpc.BindableService {

    /**
     */
    public void loadQuiz(com.quizgame.proto.QuizRequest request,
        io.grpc.stub.StreamObserver<com.quizgame.proto.QuizResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoadQuizMethod(), responseObserver);
    }

    /**
     */
    public void submitAnswer(com.quizgame.proto.AnswerRequest request,
        io.grpc.stub.StreamObserver<com.quizgame.proto.AnswerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitAnswerMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoadQuizMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.quizgame.proto.QuizRequest,
                com.quizgame.proto.QuizResponse>(
                  this, METHODID_LOAD_QUIZ)))
          .addMethod(
            getSubmitAnswerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.quizgame.proto.AnswerRequest,
                com.quizgame.proto.AnswerResponse>(
                  this, METHODID_SUBMIT_ANSWER)))
          .build();
    }
  }

  /**
   */
  public static final class GameplayStub extends io.grpc.stub.AbstractAsyncStub<GameplayStub> {
    private GameplayStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameplayStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameplayStub(channel, callOptions);
    }

    /**
     */
    public void loadQuiz(com.quizgame.proto.QuizRequest request,
        io.grpc.stub.StreamObserver<com.quizgame.proto.QuizResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoadQuizMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submitAnswer(com.quizgame.proto.AnswerRequest request,
        io.grpc.stub.StreamObserver<com.quizgame.proto.AnswerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitAnswerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GameplayBlockingStub extends io.grpc.stub.AbstractBlockingStub<GameplayBlockingStub> {
    private GameplayBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameplayBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameplayBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.quizgame.proto.QuizResponse loadQuiz(com.quizgame.proto.QuizRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoadQuizMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.quizgame.proto.AnswerResponse submitAnswer(com.quizgame.proto.AnswerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitAnswerMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GameplayFutureStub extends io.grpc.stub.AbstractFutureStub<GameplayFutureStub> {
    private GameplayFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameplayFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameplayFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.quizgame.proto.QuizResponse> loadQuiz(
        com.quizgame.proto.QuizRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoadQuizMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.quizgame.proto.AnswerResponse> submitAnswer(
        com.quizgame.proto.AnswerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitAnswerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOAD_QUIZ = 0;
  private static final int METHODID_SUBMIT_ANSWER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GameplayImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GameplayImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOAD_QUIZ:
          serviceImpl.loadQuiz((com.quizgame.proto.QuizRequest) request,
              (io.grpc.stub.StreamObserver<com.quizgame.proto.QuizResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_ANSWER:
          serviceImpl.submitAnswer((com.quizgame.proto.AnswerRequest) request,
              (io.grpc.stub.StreamObserver<com.quizgame.proto.AnswerResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GameplayBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GameplayBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.quizgame.proto.QuizGameProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Gameplay");
    }
  }

  private static final class GameplayFileDescriptorSupplier
      extends GameplayBaseDescriptorSupplier {
    GameplayFileDescriptorSupplier() {}
  }

  private static final class GameplayMethodDescriptorSupplier
      extends GameplayBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GameplayMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GameplayGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GameplayFileDescriptorSupplier())
              .addMethod(getLoadQuizMethod())
              .addMethod(getSubmitAnswerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
