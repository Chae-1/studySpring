package springEx.springEx;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Proxy extends LogTest{
    private LogTest target;

    public Proxy(LogTest target) {
        this.target = target;
    }

    @Override
    public void printS() {
        log.info("asdadsada");
        target.printS();
        log.info("asdasd");
    }
}
