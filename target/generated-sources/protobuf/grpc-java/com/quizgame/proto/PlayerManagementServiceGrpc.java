package com.quizgame.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: quizgame.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PlayerManagementServiceGrpc {

  private PlayerManagementServiceGrpc() {}

  public static final String SERVICE_NAME = "quizgame.PlayerManagementService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.quizgame.proto.RegisterPlayerRequest,
      com.quizgame.proto.RegisterPlayerResponse> getRegisterPlayerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterPlayer",
      requestType = com.quizgame.proto.RegisterPlayerRequest.class,
      responseType = com.quizgame.proto.RegisterPlayerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.quizgame.proto.RegisterPlayerRequest,
      com.quizgame.proto.RegisterPlayerResponse> getRegisterPlayerMethod() {
    io.grpc.MethodDescriptor<com.quizgame.proto.RegisterPlayerRequest, com.quizgame.proto.RegisterPlayerResponse> getRegisterPlayerMethod;
    if ((getRegisterPlayerMethod = PlayerManagementServiceGrpc.getRegisterPlayerMethod) == null) {
      synchronized (PlayerManagementServiceGrpc.class) {
        if ((getRegisterPlayerMethod = PlayerManagementServiceGrpc.getRegisterPlayerMethod) == null) {
          PlayerManagementServiceGrpc.getRegisterPlayerMethod = getRegisterPlayerMethod =
              io.grpc.MethodDescriptor.<com.quizgame.proto.RegisterPlayerRequest, com.quizgame.proto.RegisterPlayerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterPlayer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizgame.proto.RegisterPlayerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizgame.proto.RegisterPlayerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PlayerManagementServiceMethodDescriptorSupplier("RegisterPlayer"))
              .build();
        }
      }
    }
    return getRegisterPlayerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PlayerManagementServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PlayerManagementServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PlayerManagementServiceStub>() {
        @java.lang.Override
        public PlayerManagementServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PlayerManagementServiceStub(channel, callOptions);
        }
      };
    return PlayerManagementServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PlayerManagementServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PlayerManagementServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PlayerManagementServiceBlockingStub>() {
        @java.lang.Override
        public PlayerManagementServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PlayerManagementServiceBlockingStub(channel, callOptions);
        }
      };
    return PlayerManagementServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PlayerManagementServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PlayerManagementServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PlayerManagementServiceFutureStub>() {
        @java.lang.Override
        public PlayerManagementServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PlayerManagementServiceFutureStub(channel, callOptions);
        }
      };
    return PlayerManagementServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class PlayerManagementServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerPlayer(com.quizgame.proto.RegisterPlayerRequest request,
        io.grpc.stub.StreamObserver<com.quizgame.proto.RegisterPlayerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterPlayerMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterPlayerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.quizgame.proto.RegisterPlayerRequest,
                com.quizgame.proto.RegisterPlayerResponse>(
                  this, METHODID_REGISTER_PLAYER)))
          .build();
    }
  }

  /**
   */
  public static final class PlayerManagementServiceStub extends io.grpc.stub.AbstractAsyncStub<PlayerManagementServiceStub> {
    private PlayerManagementServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlayerManagementServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PlayerManagementServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerPlayer(com.quizgame.proto.RegisterPlayerRequest request,
        io.grpc.stub.StreamObserver<com.quizgame.proto.RegisterPlayerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterPlayerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PlayerManagementServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<PlayerManagementServiceBlockingStub> {
    private PlayerManagementServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlayerManagementServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PlayerManagementServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.quizgame.proto.RegisterPlayerResponse registerPlayer(com.quizgame.proto.RegisterPlayerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterPlayerMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PlayerManagementServiceFutureStub extends io.grpc.stub.AbstractFutureStub<PlayerManagementServiceFutureStub> {
    private PlayerManagementServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlayerManagementServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PlayerManagementServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.quizgame.proto.RegisterPlayerResponse> registerPlayer(
        com.quizgame.proto.RegisterPlayerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterPlayerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_PLAYER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PlayerManagementServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PlayerManagementServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_PLAYER:
          serviceImpl.registerPlayer((com.quizgame.proto.RegisterPlayerRequest) request,
              (io.grpc.stub.StreamObserver<com.quizgame.proto.RegisterPlayerResponse>) responseObserver);
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

  private static abstract class PlayerManagementServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PlayerManagementServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.quizgame.proto.QuizGameProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PlayerManagementService");
    }
  }

  private static final class PlayerManagementServiceFileDescriptorSupplier
      extends PlayerManagementServiceBaseDescriptorSupplier {
    PlayerManagementServiceFileDescriptorSupplier() {}
  }

  private static final class PlayerManagementServiceMethodDescriptorSupplier
      extends PlayerManagementServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PlayerManagementServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (PlayerManagementServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PlayerManagementServiceFileDescriptorSupplier())
              .addMethod(getRegisterPlayerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
