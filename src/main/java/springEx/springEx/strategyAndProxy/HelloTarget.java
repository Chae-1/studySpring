package springEx.springEx.strategyAndProxy;

public class HelloTarget implements Hello{
    @Override
    public String sayHello(String name) {
        return name + " hello";
    }

    @Override
    public String sayHi(String name) {
        return name + " hi";
    }

    @Override
    public String sayThankYou(String name) {
        return name + " name";
    }
}
