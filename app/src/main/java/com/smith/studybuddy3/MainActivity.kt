import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import missing.namespace.R

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var pomodoroTimer: PomodoroTimer // Declare pomodoroTimer at class level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Initialize Pomodoro Timer
        pomodoroTimer = PomodoroTimer()

        // Example usage (for testing purposes, hence hardcoded values)
        signUp("user@example.com", "password")
        logIn("user@example.com", "password")
        resetPassword("user@example.com")

        // Example study session management
        startStudySession()
        pauseStudySession()
        endStudySession()
    }

    // User Authentication Functions

    @Suppress("SameParameterValue")
    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success, update UI accordingly
                    Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()
                } else {
                    // Sign up failed, display a message to the user.
                    Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    @Suppress("SameParameterValue")
    private fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login success, update UI accordingly
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                } else {
                    // Login failed, display a message to the user.
                    Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    @Suppress("SameParameterValue")
    private fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Password reset email sent successfully
                    Toast.makeText(this, "Password reset email sent!", Toast.LENGTH_SHORT).show()
                } else {
                    // Password reset failed, display a message to the user.
                    Toast.makeText(this, "Password reset failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Timer Configuration

    // Placeholder for Pomodoro Timer implementation
    private class PomodoroTimer {
        // Example: Implementing a simple Pomodoro timer logic
        private var isTimerRunning = false
        private var currentTimerSeconds = 0

        fun startTimer() {
            if (!isTimerRunning) {
                // Start timer logic (e.g., using CountDownTimer)
                isTimerRunning = true
            }
        }

        fun pauseTimer() {
            if (isTimerRunning) {
                // Pause timer logic (e.g., pausing CountDownTimer)
                isTimerRunning = false
            }
        }

        fun resetTimer() {
            // Reset timer logic (e.g., resetting timer variables)
            isTimerRunning = false
            currentTimerSeconds = 0
        }
    }

    // Placeholder for saving timer settings to user profile
    private fun saveTimerSettings() {
        // Example: Implement saving settings logic (e.g., using SharedPreferences or Firebase Firestore)
        // Replace with your actual implementation
        Toast.makeText(this, "Timer settings saved!", Toast.LENGTH_SHORT).show()
    }

    // Study Session Management

    private fun startStudySession() {
        // Example: Implement logic to start a study session (e.g., starting a timer)
        pomodoroTimer.startTimer() // Assuming pomodoroTimer is initialized
        Toast.makeText(this, "Study session started!", Toast.LENGTH_SHORT).show()
    }

    private fun pauseStudySession() {
        // Example: Implement logic to pause a study session (e.g., pausing a timer)
        pomodoroTimer.pauseTimer() // Assuming pomodoroTimer is initialized
        Toast.makeText(this, "Study session paused!", Toast.LENGTH_SHORT).show()
    }

    private fun endStudySession() {
        // Example: Implement logic to end a study session (e.g., stopping a timer)
        pomodoroTimer.resetTimer() // Assuming pomodoroTimer is initialized
        Toast.makeText(this, "Study session ended!", Toast.LENGTH_SHORT).show()
    }

    // Button click handlers

    fun startTimer(view: android.view.View) {
        val intent = Intent(this, TimerActivity::class.java)
        startActivity(intent)
    }

    fun logOut(view: android.view.View) {
        auth.signOut()
        // Optionally navigate to login activity or exit the app
        Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show()
    }
}
