GRPC - Server Application
=========================

StreamObserver:
    void onNext(V value);
    void onError(Throwable t);
    void onCompleted();

enable-reflection property: true to access it from postman or swagger..etc

is grpc server and other service server are different?


-> How grpc clients talk to server?
- Using stub objects, generated
- Read about different types of stubs. Blocking/Nonblocking..etc