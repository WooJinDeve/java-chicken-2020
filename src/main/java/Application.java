import domain.runner.Runner;
import view.InputView;

public class Application {
    // TODO 구현 진행
    public static void main(String[] args) {
        while (true){
            validateRunner(InputView.inputMain()).runType();
        }
    }


    private static Runner validateRunner(int runnerId){
        try {
            return Runner.findById(runnerId);
        }catch (RuntimeException e){
            return validateRunner(InputView.inputMain());
        }
    }
}
