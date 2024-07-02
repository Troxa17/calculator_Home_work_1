package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Spinner spinner = binding.spinnerOperation;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.arrayOperations,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        binding.buttonCalculate.setOnClickListener(total());


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private View.OnClickListener total() {
        return v -> {
            try {
                String num1 = binding.Num1.getText().toString();
                String num2 = binding.Num2.getText().toString();
                double convNum1 = Double.parseDouble(num1);
                double convNum2 = Double.parseDouble(num2);
                String choise = binding.spinnerOperation.getSelectedItem().toString();

                double result;
                switch (choise) {
                    case "+":
                        result = convNum1 + convNum2;
                        break;
                    case "-":
                        result = convNum1 - convNum2;
                        break;
                    case "*":
                        result = convNum1 * convNum2;
                        break;
                    case "/":
                        if (convNum2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = convNum1 / convNum2;
                        break;
                    case "^":
                        result = Math.pow(convNum1, convNum2);
                        break;
                    default:
                        throw new UnsupportedOperationException("Operation not supported");
                }

                binding.Total.setText(String.valueOf(result));
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
            } catch (ArithmeticException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (UnsupportedOperationException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
