package com.example.mad155_toolbarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast

//1. implement support for android.support.appcompat.v7 in the build.gradle file [don't implement V7 support use appcompat.widget to get toolbar]
// Note this errors - I believe AS wants the use of widget.toolbar in which case implenting v7 support is not needed

//2. turn off the default app bar in either the themes or the manifest file
//3. add a tool bar in the activity xlm file. use code view: this is broken up into attributes, styling, and constraint sections in the current xml file
//4 add a resouce dir and xml - see menu xlm for code and styling


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set the Actionbar/Toolbar
        setSupportActionBar(findViewById(R.id.topBar))
        // up/back button - home navigation enabled/disabled
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // function on create of the top/tool bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topbar, menu)//load the toolbar into the activity

        // get the menu search option in the onCreate
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                // Do something when action item collapses
                Log.i("action", "collapsed menu item")
                return true // Return true to collapse action view
            }

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                // Do something when expanded
                Log.i("action", "expanded menu item")
                return true // Return true to expand action view
            }
        }
        // Get the MenuItem for the action item
        val actionMenuItem = menu?.findItem(R.id.action_search)

        // Assign the listener to that action item
        actionMenuItem?.setOnActionExpandListener(expandListener)

        return true
        //return super.onCreateOptionsMenu(menu)
    }


        // menu items onSelect function - Note this returns a boolean
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_print) {
            Toast.makeText(this, "Print selected", Toast.LENGTH_SHORT).show()
            Log.i("action", "log test")
            true

        } else if (item.itemId == R.id.action_search){
            Toast.makeText(this, "You searched", Toast.LENGTH_SHORT).show()
            true

        } else if (item.itemId == R.id.action_share){
            Toast.makeText(this, "Share selected", Toast.LENGTH_SHORT).show()
            true

        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}