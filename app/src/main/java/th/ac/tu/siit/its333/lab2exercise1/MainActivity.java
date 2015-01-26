package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;
    int ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }

    public void recalculate() {
        //Calculate the expression and display the output

        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");

        ans = Integer.parseInt(tokens[0]);

        for (int i = 1 ; i < tokens.length ; i+=2)
        {
            if(tokens[i].equals("+"))
            {
                ans += Integer.parseInt(tokens[i+1]);
            }
            if(tokens[i].equals("-"))
            {
                ans -= Integer.parseInt(tokens[i+1]);
            }
            if(tokens[i].equals("*"))
            {
                ans *= Integer.parseInt(tokens[i+1]);
            }
            if(tokens[i].equals("/"))
            {
                ans /= Integer.parseInt(tokens[i+1]);
            }

        }
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(Integer.toString(ans));

    }
    public void equalClicked(View v){
        expr = new StringBuffer();
        expr.append(Integer.toString(ans));

        updateExprDisplay();
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(" ");
    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        String op = ((TextView)v).getText().toString();
        //IF the last character in expr is not an operator and expr is not "",
        if(expr.length() > 0) {
            if (!(expr.charAt(expr.length() - 1) == '+' || expr.charAt(expr.length() - 1) == '-' || expr.charAt(expr.length() - 1) == '*'
                    || expr.charAt(expr.length() - 1) == '/')) {
                //THEN append the clicked operator and updateExprDisplay,
                expr.append(op);
                updateExprDisplay();
            }
        }
        //ELSE do nothing
    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        updateExprDisplay();
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            updateExprDisplay();
        }
        if(expr.length() > 0) {
            recalculate();
        }
    }

    double memory;
    public void mButtonClicked(View v){
        int id = v.getId();

        switch (id){
            case R.id.madd:
                memory = memory + ans;
                Toast t = Toast.makeText(this.getApplicationContext(),
                        "Mem add", Toast.LENGTH_SHORT);
                t.show();
                break;
            case R.id.msub:
                memory = memory - ans;
                Toast p = Toast.makeText(this.getApplicationContext(),
                        "Mem subtract", Toast.LENGTH_SHORT);
                p.show();
                break;
            case R.id.mr:
                TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
                tvExpr.setText(Double.toString(memory));
                Toast q = Toast.makeText(this.getApplicationContext(),
                        "MR", Toast.LENGTH_SHORT);
                q.show();
                break;
            case R.id.mc:
                memory = 0;
                Toast r = Toast.makeText(this.getApplicationContext(),
                        "MC", Toast.LENGTH_SHORT);
                r.show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
