package g313.gusev.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

// Работа выполнена Гусевым Сергеем 313 группа

public class MainActivity extends AppCompatActivity {

    Spinner spinFrom;
    Spinner spinTo;
    EditText editFrom;
    TextView textTo;
    ArrayAdapter<String> units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinFrom = findViewById(R.id.spinFrom);
        spinTo = findViewById(R.id.spinTo);
        editFrom = findViewById(R.id.editTextFrom);
        textTo = findViewById(R.id.textViewPoints);

        units = new <String> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        units.add("mm");
        units.add("cm");
        units.add("m");
        units.add("km");

        spinFrom.setAdapter(units);
        spinTo.setAdapter(units);
    }

    private int[] findUnits(String selFrom, String selTo)
    {
        int f = 0, t = 0;

        for (int i = 0; i < units.getCount(); i++)
        {
            if (selFrom == units.getItem(i)) f = i;
            if (selTo == units.getItem(i)) t = i;
        }

        int[] ans = {f, t};
        return ans;

    }

    public void onConverter(View v)
    {
        double[] sizeUnits = {1.0, 1000.0, 100000.0, 1000000.0};
        String selFrom = (String) spinFrom.getSelectedItem();
        String selTo = (String) spinTo.getSelectedItem();
        double res = 0.0;

        double numb;

        try
        {
            numb = Float.parseFloat(editFrom.getText().toString());
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Ошибка! Неверный ввод данных!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Из расстояния между индексами, вычисляем необходимые данные
        int[] fromTo = findUnits(selFrom, selTo);
        int distance = fromTo[0] - fromTo[1];

        if (distance >= 0)
        {
            res = numb * sizeUnits[distance];
        }
        else
        {
            res = numb / sizeUnits[sizeUnits.length - (sizeUnits.length - Math.abs(distance) - 1) - 1];
        }

        textTo.setText(String.valueOf(res));
    }
}