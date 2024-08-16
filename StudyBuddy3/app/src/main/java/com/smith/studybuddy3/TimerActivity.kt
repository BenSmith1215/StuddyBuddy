import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import missing.namespace.R

class TimerActivity : AppCompatActivity() {

    private lateinit var timerTextView: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var resetButton: Button

    private lateinit var timer: CountDownTimer
    private var timerRunning = false
    private var timerSeconds: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        timerTextView = findViewById(R.id.timerTextView)
        startButton = findViewById(R.id.startButton)
        pauseButton = findViewById(R.id.pauseButton)
        resetButton = findViewById(R.id.resetButton)

        setupTimer()

        startButton.setOnClickListener {
            if (!timerRunning) {
                startTimer()
            }
        }

        pauseButton.setOnClickListener {
            if (timerRunning) {
                pauseTimer()
            }
        }

        resetButton.setOnClickListener {
            resetTimer()
        }
    }

    private fun setupTimer() {
        timer = object : CountDownTimer(timerSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerSeconds = millisUntilFinished / 1000
                updateTimerUI()
            }

            override fun onFinish() {
                timerRunning = false
                updateTimerUI()
            }
        }
    }

    private fun startTimer() {
        timer.start()
        timerRunning = true
        updateTimerUI()
    }

    private fun pauseTimer() {
        timer.cancel()
        timerRunning = false
        updateTimerUI()
    }

    private fun resetTimer() {
        timer.cancel()
        timerSeconds = 0
        timerRunning = false
        updateTimerUI()
    }

    private fun updateTimerUI() {
        val minutes = (timerSeconds / 60).toInt()
        val seconds = (timerSeconds % 60).toInt()
        timerTextView.text = String.format("%02d:%02d", minutes, seconds)

        startButton.isEnabled = !timerRunning
        pauseButton.isEnabled = timerRunning
        resetButton.isEnabled = !timerRunning
    }

    fun startTimer(view: View) {}
}
