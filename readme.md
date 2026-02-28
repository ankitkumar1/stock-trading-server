GRPC - Server Application
=========================

StreamObserver:
    void onNext(V value);
    void onError(Throwable t);
    void onCompleted();

enable-reflection property: true to access it from postman or swagger..etc

is grpc server and other service server are different?
1. Communication Style: 
   - gRPC is Remote Procedure Call (RPC): client calls server functions as if they were local methods.
   - Other server using Representational State Transfer (REST)
2. Protocol
    - gRPC ses HTTP/2 exclusively, which supports multiplexing and header compression.
    - Typically uses HTTP/1.1, though can run over HTTP/2.

gRPC Vs Websocket
gRPC is generally better for backend-to-backend or backend-to-mobile communication, 
while WebSockets are better for web browser clients
gRPC: Supports Transport Layer Security (TLS) for encryption by default. Uses Token-based authentication (JWT/OAuth2).
WebSockets: Uses Secure WebSockets (wss://) for encryption (analogous to HTTPS). Security requires custom implementation (e.g., JWT validation during the handshake)

Realtime comparison :       gRPC                             Websocket
Throughput          : High (Efficient binary)	        High (Depends on framing)
Latency	            : Extremely Low (HTTP/2)	        Very Low
CPU Usage	        : Lower (due to efficient parsing)	Higher (if parsing JSON)
RAM Usage	        : Low per connection	            Slightly higher per connection
Scalability	        : High (Easier with HTTP/2)	        Harder (Stateful nature)

- What are the generated stub classes.
  - stub objects are client-side proxy classes
  - Purpose: Act as a "stand-in" or proxy for the remote service, hiding the complexity of HTTP/2 and binary serialization.
  - Types of Stubs:
    - Blocking/Synchronous Stub (newBlockingStub): The client waits for the server response. Simplest for unary calls.
    - Asynchronous Stub (newStub): Non-blocking; uses StreamObserver callbacks to handle responses, necessary for streaming.
    - Future Stub (newFutureStub): Returns a ListenableFuture for asynchronous, non-streaming calls.