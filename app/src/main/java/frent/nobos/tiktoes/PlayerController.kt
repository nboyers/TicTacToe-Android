package frent.nobos.tiktoes


import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import frent.nobos.tiktoes.databinding.ActivityMainBinding


class PlayerController {


    enum class Turn {
        NOUGHT,
        CROSS
    }
    /**
     * Helper Objects
     */
    companion object A {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

     lateinit var binding: ActivityMainBinding
     private var boardList = mutableListOf<Button>()

    private var crossesCard = 0
    private var noughtsCard = 0



    /**
     * Initializes the binding of the buttons
     * in the onCreate() func and creates a
     * listeners for each button
     */
    fun initBoard() {
        boardList.add(binding.a1)
        binding.a1.setOnClickListener {
            boardTapped(it)
        }

        boardList.add(binding.a2)
        binding.a2.setOnClickListener {
            boardTapped(it)
        }

        boardList.add(binding.a3)
        binding.a3.setOnClickListener {
            boardTapped(it)
        }

        boardList.add(binding.b1)
        binding.b1.setOnClickListener {
            boardTapped(it)
        }

        boardList.add(binding.b2)
        binding.b2.setOnClickListener {
            boardTapped(it)
        }

        boardList.add(binding.b3)
        binding.b3.setOnClickListener {
            boardTapped(it)
        }

        boardList.add(binding.c1)
        binding.c1.setOnClickListener {
            boardTapped(it)
        }

        boardList.add(binding.c2)
        binding.c2.setOnClickListener {
            boardTapped(it)
        }

        boardList.add(binding.c3)
        binding.c3.setOnClickListener {
            boardTapped(it)
        }

        Log.d(TAG, "initBoard: COMPLETED!")
    }

    /**
     * Method that runs the game when the user taps the screen
     * @param view Place the user tapped to interact with the UI
     */
    private fun boardTapped(view: View) {
        Log.d(TAG, "boardTapped: I am being Called")
        if (view !is Button)
            return

        addToBoard(view)

        if(checkForVictory(NOUGHT)) {
            noughtsCard += 1
             result("Noughts Win!")
        }

        if(checkForVictory(CROSS)) {
            crossesCard += 1
           result("Crosses Win!")
        }

        if(fullBoard()){
          result("Draw")
        }
    }

    /**
     * Method takes the button text as a string and returns if
     * that piece won or not as a Boolean
     * @param s - The piece checking for a win
     * @return Game win or Not
     */
    private fun checkForVictory(s: String): Boolean {
        //Horizontal Win
        if (match(binding.a1,s)&&match(binding.a2,s)&&match(binding.a3,s))
            return true
        if (match(binding.b1,s)&&match(binding.b2,s)&&match(binding.b3,s))
            return true
        if (match(binding.c1,s)&&match(binding.c2,s)&&match(binding.c3,s))
            return true
        //Vertical Win
        if (match(binding.a1,s)&&match(binding.b1,s)&&match(binding.c1,s))
            return true
        if (match(binding.a2,s)&&match(binding.b2,s)&&match(binding.c2,s))
            return true
        if (match(binding.a3,s)&&match(binding.b3,s)&&match(binding.c3,s))
            return true
        // Diagonal Victory
        if (match(binding.a1,s)&&match(binding.b2,s)&&match(binding.c3,s))
            return true
        if (match(binding.a3,s)&&match(binding.b2,s)&&match(binding.c1,s))
            return true

        return false
    }

    /**
     * Method that checks if a button at a certain placement is
     * equal to the symbol
     * @param button - The button the user clicks on to play the game
     * @param symbol - The value X or O
     * @return The button text matches the symbol or not
     *
     */
    private fun match (button: Button, symbol: String): Boolean = button.text == symbol

    /**
     * Method that an Alert box shown to the user
     * @param title - the alert text that will be shown
     */
    private fun result(title: String) {
        val message = "\nNoughts $noughtsCard\n\nCrosses $crossesCard"

        // FIXME: I break whenever I am called
        AlertDialog.Builder(AppCompatActivity())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset") { _,_->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    /**
     * Resets the game board back to an empty slate
     */
    private fun resetBoard() {
        for (button in boardList){
            button.text = ""
        }
        if (firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if (firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT
        currentTurn = firstTurn
        setTurnLabel()
    }

    /**
     * Checks to see if the board is full
     * @return - Game board is full
     */
    private fun fullBoard(): Boolean {
        for(button in boardList) {
            if (button.text.isEmpty()){
                return false
            }
        }
        return true
    }

    /**
     * Method that assigns the empty button string to an X or O
     * @param button - button clicked by the user
     */
    private fun addToBoard(button: Button) {
        if(button.text.isNotBlank())
            return

        if (currentTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        } else if (currentTurn == Turn.CROSS) {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    /**
     * Flips who's turn it is on the game board menu
     */
    private fun setTurnLabel() {
        var turnText = ""
        if(currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        else if(currentTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"

        binding.turnTV.text = turnText
    }
}