package com.sajjad.test_customizewebview

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.*

class MainActivity : AppCompatActivity() {
    /*
    var mCustomTabsClient: CustomTabsClient ?= null
*/

    lateinit var serviceConnection: CustomTabsServiceConnection
    lateinit var client: CustomTabsClient
    lateinit var session: CustomTabsSession
    var builder = CustomTabsIntent.Builder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(name: ComponentName, mClient: CustomTabsClient) {
                Log.d("Service", "Connected")
                client = mClient
                client.warmup(0L)
                val callback = RabbitCallback()
                session = mClient.newSession(callback)!!
                builder.setSession(session)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("Service", "Disconnected")
            }
        }
        CustomTabsClient.bindCustomTabsService(this, "com.android.chrome", serviceConnection)



        val url = Uri.parse("https://www.google.com")
        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, url)

        /*
        // set url
        val url = Uri.parse("https://nabzgroup.com/webapp/user")

        //
        val packageName = "com.android.chrome" // Change when in stable
        val mCustomTabServiceConnection: CustomTabsServiceConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
                mCustomTabsClient = client
            }

            override fun onServiceDisconnected(name: ComponentName) {

            }
        }
        CustomTabsClient.bindCustomTabsService(this, packageName, mCustomTabServiceConnection)
        mCustomTabsClient?.warmup(0L)

        val mCustomTabSession = mCustomTabsClient?.newSession(null)
        mCustomTabSession?.mayLaunchUrl(url, null, null)

        // init builder
        val ctBuilder = CustomTabsIntent.Builder(mCustomTabSession)

        // change color
        val colorInt: Int = Color.parseColor("#FF00B303") //red
        val defaultColors = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(colorInt)
            .build()
        ctBuilder.setDefaultColorSchemeParams(defaultColors)

        //custom action - not used :(
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_emoji)
        val intent = Intent(this, SecondActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 1000, intent, PendingIntent.FLAG_IMMUTABLE)
        ctBuilder.setActionButton(bitmap, "Emoji desc", pendingIntent)

        // show webView
        val ctIntent = ctBuilder.build()
        ctIntent.launchUrl(this, url)

 */
    }

    override fun onStart() {
        super.onStart()
        CustomTabsClient.bindCustomTabsService(this, "com.android.chrome", serviceConnection)
    }


    class RabbitCallback : CustomTabsCallback() {
        override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
            super.onNavigationEvent(navigationEvent, extras)
            Log.d("Nav", navigationEvent.toString())
            when (navigationEvent) {
                1 -> Log.d("Navigation", "Start") // NAVIGATION_STARTED
                2 -> Log.d("Navigation", "Finished") // NAVIGATION_FINISHED
                3 -> Log.d("Navigation", "Failed") // NAVIGATION_FAILED
                4 -> Log.d("Navigation", "Aborted") // NAVIGATION_ABORTED
                5 -> Log.d("Navigation", "Tab Shown") // TAB_SHOWN
                6 -> Log.d("Navigation", "Tab Hidden") // TAB_HIDDEN
                else -> Log.d("Navigation", "Else")
            }
        }
    }
}