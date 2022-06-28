package org.example.chatbox

class Utils {
    companion object {
        private const val SECOND_MILLIS = 1000
        private const val MINUTES_MILLIS = 60 * SECOND_MILLIS
        private const val HOURS_MILLIS =  60 * MINUTES_MILLIS
        private const val DAYS_MILLIS = 24 * HOURS_MILLIS

        fun getTimeAgo(time: Long): String? {
            val now: Long = System.currentTimeMillis()
            if(time > now || time < 0) {
                return null
            }

            val diff = now -time
            return when {
                diff < MINUTES_MILLIS -> {
                    "just now"
                }
                diff < 2 * MINUTES_MILLIS -> {
                    "a minute ago"
                }
                diff < 50 * MINUTES_MILLIS -> {
                    (diff/ MINUTES_MILLIS).toString() + " minutes ago"
                }
                diff < 90 * MINUTES_MILLIS -> {
                    "an hour ago"
                }
                diff < 24 * HOURS_MILLIS -> {
                    (diff/ HOURS_MILLIS).toString() + " hours ago"
                }
                diff < 48 * HOURS_MILLIS -> {
                    "yesterday"
                }
                else -> {
                    (diff/ DAYS_MILLIS).toString() + " days ago"
                }
            }
        }
    }
}