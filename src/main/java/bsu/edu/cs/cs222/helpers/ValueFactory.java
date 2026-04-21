package bsu.edu.cs.cs222.helpers;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class ValueFactory {
    public static void setFactory(Spinner<Integer> spinner, int userPoints) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(25, userPoints, 25) {
                    @Override
                    public void increment(int steps) {
                        setValue(Math.min(getValue() + (steps * 25), userPoints));
                    }

                    @Override
                    public void decrement(int steps) {
                        setValue(Math.max(getValue() - (steps * 25), 25));
                    }
                };
        spinner.setValueFactory(valueFactory);
    }
}
