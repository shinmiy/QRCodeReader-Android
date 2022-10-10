package com.shinmiy.qrcodereader.camera

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import com.shinmiy.qrcodereader.R

class CameraFragmentMenuProvider(
    val onTapHistory: () -> Unit,
) : MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.history -> true.also { onTapHistory() }
            else -> false
        }
    }
}
