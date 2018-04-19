package edu.wit.techspotinventory2;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private..
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "laptopSpec";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_MODEL = "model";
        public static final String COLUMN_PROCESSOR = "processor";
        public static final String COLUMN_GRAPHICS = "graphics";
        public static final String COLUMN_MEMORY = "memory";

    }
    public static class FeedCountEntry implements BaseColumns {
        public static final String TABLE_NAME = "partsInventory";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_MODEL = "model";
        public static final String COLUMN_PROCESSOR = "processor";
        public static final String COLUMN_GRAPHICS = "graphics";
        public static final String COLUMN_MEMORY = "memory";

    }
}