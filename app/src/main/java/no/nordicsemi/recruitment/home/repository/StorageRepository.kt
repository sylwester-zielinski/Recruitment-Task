package no.nordicsemi.recruitment.home.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val SHARED_PREFS_NAME = "storage"
private const val TASKS_KEY = "tasks"

class StorageRepository @Inject constructor(
    @ApplicationContext
    context: Context
) {

    private val sp = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    var tasks: List<String>
        get() = sp.getStringSet(TASKS_KEY, null)?.toList() ?: emptyList()
        set(value) {
            sp.edit().putStringSet(TASKS_KEY, value.toSet()).commit()
        }
}
