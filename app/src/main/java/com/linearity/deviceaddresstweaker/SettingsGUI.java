package com.linearity.deviceaddresstweaker;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.topjohnwu.superuser.Shell;

import java.util.ArrayList;
import java.util.List;

public class SettingsGUI extends Activity {
    public static List<Pair<String,Boolean>> spListDefault = initSPList();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Shell.getShell();
        setContentView(R.layout.settings);
        RecyclerView recyclerView = findViewById(R.id.options);
        EditText inputPackageName = findViewById(R.id.input_packagename);
        TextView refreshButton = findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(v -> {
            try {
                String packageName = String.valueOf(inputPackageName.getText()).replace(" ","").replace("\n", "");
                PackageManager pm = getPackageManager();
                pm.getPackageInfo(packageName, 0);
                SettingAdapter settingAdapter = new SettingAdapter(spListDefault, packageName);
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                recyclerView.setAdapter(settingAdapter);
            }catch (Exception e){
                Toast.makeText(refreshButton.getContext(), "NO PACKAGE!", Toast.LENGTH_LONG).show();
//                    LoggerLog(e);
                Log.e("[linearity]", "onClick: ", e);
            }
        });
    }

    public static List<Pair<String,Boolean>> initSPList(){
        ArrayList<Pair<String,Boolean>> DefaultResult = new ArrayList<>();
        DefaultResult.add(new Pair<>("HookJavaNetClass_HookNet",true));
        DefaultResult.add(new Pair<>("HookJavaNetClass_HookNetworkInterface",true));
        DefaultResult.add(new Pair<>("HookJavaNetClass_HookHttpUrlConnection", true));
        DefaultResult.add(new Pair<>("HookJavaNetClass_HookUrl", true));
        DefaultResult.add(new Pair<>("HookLang_HookLang", true));
        DefaultResult.add(new Pair<>("HookLang_HookRuntime", true));
        DefaultResult.add(new Pair<>("HookLang_HookThrowable", true));
        DefaultResult.add(new Pair<>("HookLang_HookObject", true));
        DefaultResult.add(new Pair<>("HookLang_HookClass", true));
        DefaultResult.add(new Pair<>("HookLang_HookStackTraceElement",true));
        DefaultResult.add(new Pair<>("HookLang_Listen2Throwable", false));
        DefaultResult.add(new Pair<>("HookLang_HookReflect_HookReflect", true));
        DefaultResult.add(new Pair<>("HookLang_HookReflect_HookModifier", true));
        DefaultResult.add(new Pair<>("HookIO", true));
        DefaultResult.add(new Pair<>("HookFile", true));
        DefaultResult.add(new Pair<>("HookIO_HookInputStream", true));
        DefaultResult.add(new Pair<>("HookIO_HookOutputStream", true));//
        DefaultResult.add(new Pair<>("HookAccountClass_HookAccounts",true));
        DefaultResult.add(new Pair<>("HookAccountClass_HookAccount", true));
        DefaultResult.add(new Pair<>("HookAccountClass_HookAccountManager", true));
        DefaultResult.add(new Pair<>("HookAccountClass_HookAccountAuthenticatorResponse", true));
        DefaultResult.add(new Pair<>("HookAccountClass_HookAuthenticatorDescription", true));
        DefaultResult.add(new Pair<>("HookAccountClass_HookAbstractAccountAuthenticator", true));
        DefaultResult.add(new Pair<>("HookAppClass_HookApp", true));
        DefaultResult.add(new Pair<>("HookAppClass_HookApplication", true));
        DefaultResult.add(new Pair<>("HookAppClass_HookActivity", true));
        DefaultResult.add(new Pair<>("HookAppClass_HookActivityManager", true));
        DefaultResult.add(new Pair<>("HookAppClass_HookContextImpl", true));
        DefaultResult.add(new Pair<>("HookAppClass_HookKeyguardManager", true));
        DefaultResult.add(new Pair<>("HookAppClass_HookLifecycleCallbacks", false));
        DefaultResult.add(new Pair<>("HookBluetoothClass_HookBluetooth", true));
        DefaultResult.add(new Pair<>("HookBluetoothClass_HookBluetoothAdapter", true));
        DefaultResult.add(new Pair<>("HookContentClass_HookContent", true));
        DefaultResult.add(new Pair<>("HookContentClass_HookContext", true));
        DefaultResult.add(new Pair<>("HookContentClass_HookClipboardManager", true));
        DefaultResult.add(new Pair<>("HookContentClass_HookSharedPreferences", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_HookHardware", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_HookSensor", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_HookSensorManager", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_HookSensorEvent", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_HookGeomagneticField", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_input", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_input_HookInputDeviceBatteryState", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_usb", true));
        DefaultResult.add(new Pair<>("HookHardwareClass_usb_HookUSBClass", true));
        DefaultResult.add(new Pair<>("HookLocationClass_HookLocation", true));
        DefaultResult.add(new Pair<>("HookMTPClass", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookNet", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookNetworkCapabilities", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookNetworkInfo", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookConnectivityManager", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookPlatformVpnProfile", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookVpnManager", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookInetAddress", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookVpnService", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookTelephonyNetworkSpecifier", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookRouteInfo", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookProxyInfo", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookProxy", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookNetworkRequest", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookNetwork", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookMacAddress", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookLocalSocketAddress", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookLinkProperties", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookLinkAddress", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookIpSecTransform", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookIpPrefix", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookIkev2VpnProfile", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookConnectivityDiagnosticsManager", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookCaptivePortal", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookUri", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookDnsResolver", false));
        DefaultResult.add(new Pair<>("HookNetClass_HookDhcpInfo", false));
        DefaultResult.add(new Pair<>("HookOsClass_HookOs", true));
        DefaultResult.add(new Pair<>("HookOsClass_HookBuild", true));
        DefaultResult.add(new Pair<>("HookOsClass_HookEnvironment", true));
        DefaultResult.add(new Pair<>("HookOsClass_HookStatFs", true));
        DefaultResult.add(new Pair<>("HookOsClass_HookPowerManager", true));
        DefaultResult.add(new Pair<>("HookOsClass_HookSystemProperties",true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWifi", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWifiInfo", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWifiManager", false));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWifiNetworkSpecifier", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWifiNetworkSuggestion", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWpsInfo", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWifiEnterpriseConfig", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWifiConfiguration", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookSupplicantState", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookSoftApConfiguration", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookScanResult", true));
        DefaultResult.add(new Pair<>("HookNetClass_HookWifiClass_HookWifiLocks", false));
        DefaultResult.add(new Pair<>("HookContentClass_HookResClass_HookRes", true));
        DefaultResult.add(new Pair<>("HookContentClass_HookResClass_HookResources", true));
        DefaultResult.add(new Pair<>("HookProviderClass_HookProvider", true));
        DefaultResult.add(new Pair<>("HookProviderClass_HookMediaStore", true));
        DefaultResult.add(new Pair<>("HookProviderClass_HookSettings", true));
        DefaultResult.add(new Pair<>("HookProviderClass_HookSecure", true));
        DefaultResult.add(new Pair<>("HookTelephonyClass_HookTelephony", true));
        DefaultResult.add(new Pair<>("HookTelephonyClass_HookTelephonyManager", true));
        DefaultResult.add(new Pair<>("HookTelephonyClass_HookGSM", true));
        DefaultResult.add(new Pair<>("HookTelephonyClass_HookSmsManager", true));
        DefaultResult.add(new Pair<>("HookTelephonyClass_HookSmsMessage", true));
        DefaultResult.add(new Pair<>("HookTelephonyClass_HookGSMCellLocation", true));
        DefaultResult.add(new Pair<>("HookTelephonyClass_HookCdmaCellLocation", true));
        DefaultResult.add(new Pair<>("HookTelephonyClass_HookHookExactLocation", true));


        return DefaultResult;
    }
}
