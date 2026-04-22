package org.fdroid.fdroid.nearby;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import cc.mvdan.accesspoint.WifiApControl;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import java.util.ArrayList;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.peers.Peer;

/* JADX INFO: loaded from: classes2.dex */
public class StartSwapView extends SwapView {
    private static final String TAG = "StartSwapView";
    private final BluetoothAdapter bluetooth;
    private MaterialSwitch bluetoothSwitch;
    private final CompoundButton.OnCheckedChangeListener onBluetoothSwitchToggled;
    private final BroadcastReceiver onWifiNetworkChanged;
    private PeopleNearbyAdapter peopleNearbyAdapter;
    private ListView peopleNearbyList;
    private CircularProgressIndicator peopleNearbyProgress;
    private TextView peopleNearbyText;
    private TextView textBluetoothVisible;
    private TextView viewBluetoothId;
    private TextView viewWifiId;
    private TextView viewWifiNetwork;

    public StartSwapView(Context context) {
        super(context);
        this.bluetooth = BluetoothAdapter.getDefaultAdapter();
        this.onWifiNetworkChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.StartSwapView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                StartSwapView.this.uiUpdateWifiNetwork();
            }
        };
        this.onBluetoothSwitchToggled = new CompoundButton.OnCheckedChangeListener() { // from class: org.fdroid.fdroid.nearby.StartSwapView.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(false) for Bluetooth swap, disabling Bluetooth swap.");
                    BluetoothManager.stop(StartSwapView.this.getContext());
                    StartSwapView.this.textBluetoothVisible.setText(R.string.swap_not_visible_bluetooth);
                    StartSwapView.this.viewBluetoothId.setVisibility(8);
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(false) for Bluetooth swap, Bluetooth swap disabled successfully.");
                } else if (ContextCompat.checkSelfPermission(StartSwapView.this.getContext(), "android.permission.BLUETOOTH_CONNECT") == 0 && ContextCompat.checkSelfPermission(StartSwapView.this.getContext(), "android.permission.BLUETOOTH_SCAN") == 0) {
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(true) for Bluetooth swap, prompting user as to whether they want to enable Bluetooth.");
                    StartSwapView.this.getActivity().startBluetoothSwap();
                    StartSwapView.this.textBluetoothVisible.setText(R.string.swap_visible_bluetooth);
                    StartSwapView.this.viewBluetoothId.setText(StartSwapView.this.bluetooth.getName());
                    StartSwapView.this.viewBluetoothId.setVisibility(0);
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(true) for Bluetooth swap (prompting user or setup Bluetooth complete)");
                } else {
                    Toast.makeText(StartSwapView.this.getContext(), R.string.swap_bluetooth_permissions, 1).show();
                    StartSwapView.this.bluetoothSwitch.setChecked(false);
                    return;
                }
                SwapService.putBluetoothVisibleUserPreference(z);
            }
        };
    }

    public StartSwapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.bluetooth = BluetoothAdapter.getDefaultAdapter();
        this.onWifiNetworkChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.StartSwapView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                StartSwapView.this.uiUpdateWifiNetwork();
            }
        };
        this.onBluetoothSwitchToggled = new CompoundButton.OnCheckedChangeListener() { // from class: org.fdroid.fdroid.nearby.StartSwapView.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(false) for Bluetooth swap, disabling Bluetooth swap.");
                    BluetoothManager.stop(StartSwapView.this.getContext());
                    StartSwapView.this.textBluetoothVisible.setText(R.string.swap_not_visible_bluetooth);
                    StartSwapView.this.viewBluetoothId.setVisibility(8);
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(false) for Bluetooth swap, Bluetooth swap disabled successfully.");
                } else if (ContextCompat.checkSelfPermission(StartSwapView.this.getContext(), "android.permission.BLUETOOTH_CONNECT") == 0 && ContextCompat.checkSelfPermission(StartSwapView.this.getContext(), "android.permission.BLUETOOTH_SCAN") == 0) {
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(true) for Bluetooth swap, prompting user as to whether they want to enable Bluetooth.");
                    StartSwapView.this.getActivity().startBluetoothSwap();
                    StartSwapView.this.textBluetoothVisible.setText(R.string.swap_visible_bluetooth);
                    StartSwapView.this.viewBluetoothId.setText(StartSwapView.this.bluetooth.getName());
                    StartSwapView.this.viewBluetoothId.setVisibility(0);
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(true) for Bluetooth swap (prompting user or setup Bluetooth complete)");
                } else {
                    Toast.makeText(StartSwapView.this.getContext(), R.string.swap_bluetooth_permissions, 1).show();
                    StartSwapView.this.bluetoothSwitch.setChecked(false);
                    return;
                }
                SwapService.putBluetoothVisibleUserPreference(z);
            }
        };
    }

    public StartSwapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bluetooth = BluetoothAdapter.getDefaultAdapter();
        this.onWifiNetworkChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.StartSwapView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                StartSwapView.this.uiUpdateWifiNetwork();
            }
        };
        this.onBluetoothSwitchToggled = new CompoundButton.OnCheckedChangeListener() { // from class: org.fdroid.fdroid.nearby.StartSwapView.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(false) for Bluetooth swap, disabling Bluetooth swap.");
                    BluetoothManager.stop(StartSwapView.this.getContext());
                    StartSwapView.this.textBluetoothVisible.setText(R.string.swap_not_visible_bluetooth);
                    StartSwapView.this.viewBluetoothId.setVisibility(8);
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(false) for Bluetooth swap, Bluetooth swap disabled successfully.");
                } else if (ContextCompat.checkSelfPermission(StartSwapView.this.getContext(), "android.permission.BLUETOOTH_CONNECT") == 0 && ContextCompat.checkSelfPermission(StartSwapView.this.getContext(), "android.permission.BLUETOOTH_SCAN") == 0) {
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(true) for Bluetooth swap, prompting user as to whether they want to enable Bluetooth.");
                    StartSwapView.this.getActivity().startBluetoothSwap();
                    StartSwapView.this.textBluetoothVisible.setText(R.string.swap_visible_bluetooth);
                    StartSwapView.this.viewBluetoothId.setText(StartSwapView.this.bluetooth.getName());
                    StartSwapView.this.viewBluetoothId.setVisibility(0);
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(true) for Bluetooth swap (prompting user or setup Bluetooth complete)");
                } else {
                    Toast.makeText(StartSwapView.this.getContext(), R.string.swap_bluetooth_permissions, 1).show();
                    StartSwapView.this.bluetoothSwitch.setChecked(false);
                    return;
                }
                SwapService.putBluetoothVisibleUserPreference(z);
            }
        };
    }

    public StartSwapView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.bluetooth = BluetoothAdapter.getDefaultAdapter();
        this.onWifiNetworkChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.StartSwapView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                StartSwapView.this.uiUpdateWifiNetwork();
            }
        };
        this.onBluetoothSwitchToggled = new CompoundButton.OnCheckedChangeListener() { // from class: org.fdroid.fdroid.nearby.StartSwapView.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(false) for Bluetooth swap, disabling Bluetooth swap.");
                    BluetoothManager.stop(StartSwapView.this.getContext());
                    StartSwapView.this.textBluetoothVisible.setText(R.string.swap_not_visible_bluetooth);
                    StartSwapView.this.viewBluetoothId.setVisibility(8);
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(false) for Bluetooth swap, Bluetooth swap disabled successfully.");
                } else if (ContextCompat.checkSelfPermission(StartSwapView.this.getContext(), "android.permission.BLUETOOTH_CONNECT") == 0 && ContextCompat.checkSelfPermission(StartSwapView.this.getContext(), "android.permission.BLUETOOTH_SCAN") == 0) {
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(true) for Bluetooth swap, prompting user as to whether they want to enable Bluetooth.");
                    StartSwapView.this.getActivity().startBluetoothSwap();
                    StartSwapView.this.textBluetoothVisible.setText(R.string.swap_visible_bluetooth);
                    StartSwapView.this.viewBluetoothId.setText(StartSwapView.this.bluetooth.getName());
                    StartSwapView.this.viewBluetoothId.setVisibility(0);
                    Utils.debugLog(StartSwapView.TAG, "Received onCheckChanged(true) for Bluetooth swap (prompting user or setup Bluetooth complete)");
                } else {
                    Toast.makeText(StartSwapView.this.getContext(), R.string.swap_bluetooth_permissions, 1).show();
                    StartSwapView.this.bluetoothSwitch.setChecked(false);
                    return;
                }
                SwapService.putBluetoothVisibleUserPreference(z);
            }
        };
    }

    class PeopleNearbyAdapter extends ArrayAdapter<Peer> {
        PeopleNearbyAdapter(Context context) {
            super(context, 0, new ArrayList());
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.swap_peer_list_item, viewGroup, false);
            }
            Peer peer = (Peer) getItem(i);
            ((TextView) view.findViewById(R.id.peer_name)).setText(peer.getName());
            ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(ContextCompat.getDrawable(getContext(), peer.getIcon()));
            return view;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MaterialSwitch materialSwitch = this.bluetoothSwitch;
        if (materialSwitch != null) {
            materialSwitch.setOnCheckedChangeListener(null);
        }
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.onWifiNetworkChanged);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        uiInitPeers();
        uiInitBluetooth();
        uiInitWifi();
        uiInitButtons();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.onWifiNetworkChanged, new IntentFilter(WifiStateChangeService.BROADCAST));
    }

    private void uiInitButtons() {
        ((MaterialButton) findViewById(R.id.btn_send_fdroid)).setEllipsize(TextUtils.TruncateAt.END);
        findViewById(R.id.btn_send_fdroid).setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.StartSwapView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$uiInitButtons$0(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$uiInitButtons$0(View view) {
        getActivity().sendFDroid();
    }

    private void uiInitPeers() {
        this.peopleNearbyText = (TextView) findViewById(R.id.text_people_nearby);
        this.peopleNearbyList = (ListView) findViewById(R.id.list_people_nearby);
        this.peopleNearbyProgress = (CircularProgressIndicator) findViewById(R.id.searching_people_nearby);
        PeopleNearbyAdapter peopleNearbyAdapter = new PeopleNearbyAdapter(getContext());
        this.peopleNearbyAdapter = peopleNearbyAdapter;
        this.peopleNearbyList.setAdapter((ListAdapter) peopleNearbyAdapter);
        for (Peer peer : getActivity().getSwapService().getActivePeers()) {
            if (this.peopleNearbyAdapter.getPosition(peer) == -1) {
                this.peopleNearbyAdapter.add(peer);
            }
        }
        this.peopleNearbyList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: org.fdroid.fdroid.nearby.StartSwapView$$ExternalSyntheticLambda1
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.lambda$uiInitPeers$1(adapterView, view, i, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$uiInitPeers$1(AdapterView adapterView, View view, int i, long j) {
        onPeerSelected((Peer) this.peopleNearbyAdapter.getItem(i));
    }

    private void uiInitBluetooth() {
        if (this.bluetooth != null) {
            this.viewBluetoothId = (TextView) findViewById(R.id.device_id_bluetooth);
            if (ContextCompat.checkSelfPermission(getContext(), "android.permission.BLUETOOTH_CONNECT") == 0) {
                this.viewBluetoothId.setText(this.bluetooth.getName());
            }
            this.viewBluetoothId.setVisibility(this.bluetooth.isEnabled() ? 0 : 8);
            this.textBluetoothVisible = (TextView) findViewById(R.id.bluetooth_visible);
            MaterialSwitch materialSwitch = (MaterialSwitch) findViewById(R.id.switch_bluetooth);
            this.bluetoothSwitch = materialSwitch;
            materialSwitch.setOnCheckedChangeListener(this.onBluetoothSwitchToggled);
            this.bluetoothSwitch.setChecked(SwapService.getBluetoothVisibleUserPreference());
            this.bluetoothSwitch.setEnabled(true);
            this.bluetoothSwitch.setOnCheckedChangeListener(this.onBluetoothSwitchToggled);
            return;
        }
        findViewById(R.id.bluetooth_info).setVisibility(8);
    }

    private void uiInitWifi() {
        this.viewWifiId = (TextView) findViewById(R.id.device_id_wifi);
        this.viewWifiNetwork = (TextView) findViewById(R.id.wifi_network);
        uiUpdateWifiNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uiUpdateWifiNetwork() {
        this.viewWifiId.setText(FDroidApp.ipAddressString);
        this.viewWifiId.setVisibility(TextUtils.isEmpty(FDroidApp.ipAddressString) ? 8 : 0);
        WifiApControl wifiApControl = WifiApControl.getInstance(getActivity());
        if (wifiApControl != null && wifiApControl.isWifiApEnabled()) {
            WifiConfiguration configuration = wifiApControl.getConfiguration();
            TextView textView = (TextView) findViewById(R.id.wifi_visible);
            if (textView != null) {
                textView.setText(R.string.swap_visible_hotspot);
            }
            Context context = getContext();
            if (configuration == null) {
                this.viewWifiNetwork.setText(context.getString(R.string.swap_active_hotspot, context.getString(R.string.swap_blank_wifi_ssid)));
                return;
            } else {
                this.viewWifiNetwork.setText(context.getString(R.string.swap_active_hotspot, configuration.SSID));
                return;
            }
        }
        if (TextUtils.isEmpty(FDroidApp.ssid)) {
            this.viewWifiNetwork.setText(R.string.swap_no_wifi_network);
        } else {
            this.viewWifiNetwork.setText(FDroidApp.ssid);
        }
    }

    private void onPeerSelected(Peer peer) {
        getActivity().swapWith(peer);
    }
}
