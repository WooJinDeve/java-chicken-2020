package domain.runner;

import java.util.Arrays;

public enum Runner {

    Order(1){
        @Override
        public void runType() {
            OrderRunner.run();
        }
    },
    Payment(2){
        @Override
        public void runType() {
            PaymentRunner.run();
        }
    },
    Close(3){
        @Override
        public void runType() {
            System.exit(0);
        }
    };

    private int id;

    Runner(int id) {
        this.id = id;
    }

    public static Runner findById(int id){
        return Arrays.stream(Runner.values())
                .filter(runner -> runner.hasId(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public boolean hasId(int id){
        return this.id == id;
    }

    abstract public void runType();
}
