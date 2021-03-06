package com.carlospinan.androidm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @source http://inthecheesefactory.com/blog/things-you-need-to-know-about-android-m-permission-developer-edition/en
 */

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int REQUEST_CONTACTS = 0;
    private static final int REQUEST_CAMERA = 1;

    private static String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS};

    private static String[] PERMISSION_CAMERA = {Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showCamera(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermissions();
        } else {
            showCamera();
        }
    }

    private void requestCameraPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Snackbar.make(findViewById(R.id.container), R.string.permission_camera_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(MainActivity.this, PERMISSION_CAMERA,
                                            REQUEST_CAMERA);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSION_CAMERA, REQUEST_CAMERA);
        }
    }

    private void showCamera() {
        Snackbar.make(findViewById(R.id.container), R.string.camera_details,
                Snackbar.LENGTH_SHORT)
                .show();
    }


    public void showContacts(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestContactsPermissions();

        } else {
            showContactDetails();
        }
    }

    private void requestContactsPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_CONTACTS)) {
            Snackbar.make(findViewById(R.id.container), R.string.permission_contacts_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(MainActivity.this, PERMISSIONS_CONTACT,
                                            REQUEST_CONTACTS);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }

    private void showContactDetails() {
        Snackbar.make(findViewById(R.id.container), R.string.contact_details,
                Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS) {
            if (PermissionUtil.verifyPermissions(grantResults)) {
                Snackbar.make(findViewById(R.id.container), R.string.permision_available_contacts,
                        Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                Snackbar.make(findViewById(R.id.container), R.string.permissions_not_granted,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == REQUEST_CAMERA) {
            if (PermissionUtil.verifyPermissions(grantResults)) {
                Snackbar.make(findViewById(R.id.container), R.string.permision_available_contacts,
                        Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                Snackbar.make(findViewById(R.id.container), R.string.permissions_not_granted,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
