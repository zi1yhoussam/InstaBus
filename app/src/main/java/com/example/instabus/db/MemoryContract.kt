package com.example.instabus.db

import android.provider.BaseColumns


class MemoryContract  //An empty private constructor makes sure that the class is not going to be initialised.
private constructor() {
    object MemoryEntry : BaseColumns {
        const val _ID = "_id"
        const val TABLE_NAME = "memories"
        const val COLUMN_TITLE = "title"
        const val COLUMN_IMAGE = "image"
    }
}
