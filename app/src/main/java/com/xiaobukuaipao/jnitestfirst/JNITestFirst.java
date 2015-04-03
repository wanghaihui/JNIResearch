package com.xiaobukuaipao.jnitestfirst;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.nio.charset.Charset;


public class JNITestFirst extends ActionBarActivity {
    private TextView mNativeView;
    private TextView mPrimitiveView;
    private TextView mStringTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnitest_first);

        mNativeView = (TextView) findViewById(R.id.native_view);
        callNativeMethods();

        mPrimitiveView = (TextView) findViewById(R.id.primitive);
        StringBuilder sb = new StringBuilder();
        sb.append("boolean: ").append(passBooleanReturnBoolean(false)).append(System.getProperty("line.separator"))
          .append("byte: ").append(passByteReturnByte((byte)10)).append(System.getProperty("line.separator"))
          .append("char: ").append(passCharReturnChar('#')).append(System.getProperty("line.separator"))
          .append("short: ").append(passShortReturnShort((short)100)).append(System.getProperty("line.separator"))
          .append("int: ").append(passIntReturnInt(1000)).append(System.getProperty("line.separator"))
          .append("long: ").append(passLongReturnLong(4294967396L)).append(System.getProperty("line.separator"))
          .append("float: ").append(passFloatReturnFloat(1.11f)).append(System.getProperty("line.separator"))
          .append("double: ").append(passDoubleReturnDouble(11.11)).append(System.getProperty("line.separator"));

        mPrimitiveView.setText(sb.toString());

        mStringTest = (TextView) findViewById(R.id.string_test);
        sb.delete(0, sb.toString().length());
        sb.append("Android default character encoding: ").append(Charset.defaultCharset().name())
                .append(System.getProperty("line.separator"))
                .append(passStringReturnString("hello native code"));
        mStringTest.setText(sb.toString());

    }

    private void callNativeMethods() {
        int a = 10, b = 100;
        int c = NativeAddition(a, b);
        mNativeView.setText(a + "+" + b + "=" + c);
        c = NativeMultiplication(a, b);
        mNativeView.append("\n" + a + "x" + b + "=" + c);
    }

    private native int NativeAddition(int a, int b);
    private native int NativeMultiplication(int a, int b);

    private native boolean passBooleanReturnBoolean(boolean p);
    private native byte passByteReturnByte(byte p);
    private native char passCharReturnChar(char p);
    private native short passShortReturnShort(short p);
    private native int passIntReturnInt(int p);
    private native long passLongReturnLong(long p);
    private native float passFloatReturnFloat(float p);
    private native double passDoubleReturnDouble(double p);

    private native String passStringReturnString(String p);

    static {
        System.loadLibrary("JNITest");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jnitest_first, menu);
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
