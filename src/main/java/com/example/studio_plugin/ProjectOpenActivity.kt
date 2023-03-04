package com.example.studio_plugin

import com.android.tools.idea.concurrency.AndroidCoroutineScope
import com.android.tools.idea.concurrency.AndroidDispatchers.uiThread
import com.android.tools.idea.explorer.DeviceExplorerFilesUtils
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import kotlinx.coroutines.*
import java.io.File
import kotlin.coroutines.EmptyCoroutineContext


class ProjectOpenActivity : StartupActivity, DumbAware {

    override fun runActivity(project: Project) {
        val coroutineScope = AndroidCoroutineScope(project)

        GlobalScope.launch(Dispatchers.Main) {
//            callASuspendFunction()
            println("Dispatchers.Main")
        }

        runBlocking {
            downloadAndOpenFile()
        }
    }

    private suspend fun downloadAndOpenFile() = withContext(uiThread) {
        try {
            var vf = DeviceExplorerFilesUtils.findFile(File(".").toPath());
            println("========>" + vf.parent)
        }
        catch (t: Throwable) {
            println("err")
            t.printStackTrace()
        }
    }

    companion object {
        private val LOG = Logger.getInstance(
            ProjectOpenActivity::class.java
        )
    }
}