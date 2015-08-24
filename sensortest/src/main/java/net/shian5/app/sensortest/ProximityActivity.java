package net.shian5.app.sensortest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ProximityActivity extends Activity implements SensorEventListener {

    private SensorManager mgr;
    private Sensor proximity;
    private TextView disText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        this.disText = (TextView) findViewById(R.id.disText);

        this.mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        // 取得接近感應器
        this.proximity = this.mgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在這裏註冊
        this.mgr.registerListener(this, this.proximity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在這裏解除註冊
        this.mgr.unregisterListener(this, this.proximity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_proximity, menu);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        float distance = event.values[0]; // 目前的距離
        this.disText.setText(Float.toString(distance));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
