package com.exampleapp.mvpapp_kotlin.utils

import android.text.Editable
import com.exampleapp.mvpapp_kotlin.utils.Const.*
import com.exampleapp.mvpapp_kotlin.db.NotesDao
import com.exampleapp.mvpapp_kotlin.entity.Note
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun NotesDao.runExecutor(const: Enum<Const>, note: Note) {
    val executor = Executors.newSingleThreadExecutor()
    when (const) {
        INSERT -> {
            executor.submit { this.insert(note) }
            executor.shutdown()
        }
        DELETE -> {
            executor.submit { this.deleteNote(note.id) }
            executor.shutdown()
        }
        UPDATE -> {
            executor.submit { this.update(note) }
            executor.shutdown()
        }
    }

    try {
        executor.awaitTermination(3, TimeUnit.SECONDS)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}

fun NotesDao.returningDataExecutor(id: Int): String? {
    val executor = Executors.newSingleThreadExecutor()
    val future = executor.submit(Callable { this.getDataById(id) })
    executor.shutdown()
    future.get()

    return try {
        executor.awaitTermination(3, TimeUnit.SECONDS)
        future.get()
    } catch (e: InterruptedException) {
        e.printStackTrace()
        null
    }
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)