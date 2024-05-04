package com.linearity.deviceaddresstweaker;

import static com.linearity.deviceaddresstweaker.LoggerUtils.LoggerLog;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.topjohnwu.superuser.Shell;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    public List<Pair<String, Boolean>> optionsPairsList;
    public String packageName;
    public SettingAdapter(List<Pair<String, Boolean>> optionsPairsList, String packageName){
        this.optionsPairsList = optionsPairsList;
        this.packageName = packageName;
    }
    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_item, parent, false);
        return new SettingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        Pair<String,Boolean> option = optionsPairsList.get(position);
        holder.optionSwitch.setText(option.first);
        Context targContext = null;
        try {
//            targContext = holder.optionSwitch.getContext().createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY);
            SharedPreferences sp = holder.optionSwitch.getContext().getSharedPreferences(packageName + "_linearity_dat_settings", Context.MODE_PRIVATE);
            holder.optionSwitch.setChecked(sp.getBoolean(option.first,option.second));
        } catch (Exception e) {
            LoggerLog(e);
        }
        holder.optionSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isChecked = holder.optionSwitch.isChecked();

//                            Context targContext = buttonView.getContext().createPackageContext(packageName,Context.CONTEXT_IGNORE_SECURITY);
                    SharedPreferences sp = holder.optionSwitch.getContext().getSharedPreferences(packageName + "_linearity_dat_settings", Context.MODE_PRIVATE);
//                        XSharedPreferences SharedPreferences = new XSharedPreferences(packageName);
                    SharedPreferences.Editor edit = sp.edit();
//                            edit.remove(option.first);
                    edit.putBoolean(option.first, isChecked);
                    edit.apply();
                    edit.commit();
//                    File spFile = new File("/data/data/com.linearity.deviceaddresstweaker/shared_prefs/" + packageName + "_linearity_dat_settings.xml");
//                    Shell.cmd("su").exec();
//                    Runtime runtime = Runtime.getRuntime();
//                    runtime.exec("cp /data/data/com.linearity.deviceaddresstweaker/shared_prefs/" + packageName + "_linearity_dat_settings.xml /data/data/" + packageName + "/shared_prefs/" + packageName + "_linearity_dat_settings.xml");
                    Shell.getShell().newJob().add("su").exec();
                    Shell.Result execResult  = Shell.getShell().newJob().add("mkdir /data/local/tmp/linearity_dat").exec();
                    execResult  = Shell.getShell().newJob().add("mkdir /data/local/tmp/linearity_dat/shared_prefs").exec();
                    execResult = Shell.getShell().newJob().add(
                            "cp /data/data/com.linearity.deviceaddresstweaker/shared_prefs/" + packageName + "_linearity_dat_settings.xml /data/local/tmp/linearity_dat/shared_prefs/" + packageName + "_linearity_dat_settings.xml"
                            , "chmod 777 /data/local/tmp/linearity_dat/shared_prefs/" + packageName + "_linearity_dat_settings.xml"
//                            , "chmod 777 /data/data/com.linearity.deviceaddresstweaker/shared_prefs"
//                            , "chmod 777 /data/data/com.linearity.deviceaddresstweaker"
                    ).exec();
//                    Log.d("[linearity]", packageName + " onClick: " + Shell.isAppGrantedRoot() +
//                            execResult.getOut() + execResult.getErr() + execResult.isSuccess()
//                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//        holder.optionSwitch.setOnCheckedChangeListener(
//                new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        try {
//
////                            Context targContext = buttonView.getContext().createPackageContext(packageName,Context.CONTEXT_IGNORE_SECURITY);
//                            SharedPreferences sp = buttonView.getContext().getSharedPreferences(packageName + "_linearity_dat_settings", Context.MODE_PRIVATE);
////                        XSharedPreferences SharedPreferences = new XSharedPreferences(packageName);
//                            SharedPreferences.Editor edit = sp.edit();
////                            edit.remove(option.first);
//                            edit.putBoolean(option.first, isChecked);
//                            edit.apply();
//                            Log.d("[linearity]", "onCheckedChanged: " + option.first+  " " + isChecked);
//                        } catch (Exception e) {
//                            Log.e("[linearity]", String.valueOf(e));
//                        }
//                    }
//                }
//        );
    }

    @Override
    public int getItemCount() {
        return optionsPairsList.size();
    }

    public static class SettingViewHolder extends RecyclerView.ViewHolder{
        SwitchCompat optionSwitch;
        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            optionSwitch = itemView.findViewById(R.id.option_switch);
        }
    }
}
