-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keep class kotlin.Metadata { *; }

-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**
