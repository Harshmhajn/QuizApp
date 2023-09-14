package com.example.earningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.earningapp.databinding.ActivityQuizBinding
import com.example.earningapp.modelClass.Question
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    var currentQuestion  = 0
    private lateinit var questionList : ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        questionList = ArrayList<Question>()
        var image = intent.getIntExtra("cat_img",0)
        var catText = intent.getStringExtra("questionType")

        Firebase.
        firestore.collection("Question").document(catText.toString())
            .collection("question1").get().addOnSuccessListener {
                questionData ->
                questionList.clear()
                for (data in questionData.documents){
                    var question  : Question? = data.toObject(Question::class.java)
                    questionList.add(question!!)

                }
                if(questionList.size > 0)
                {

                    binding.question.text = questionList.get(currentQuestion).question
                    binding.opt1.text = questionList.get(currentQuestion).option1
                    binding.opt2.text = questionList.get(currentQuestion).option2
                    binding.opt3.text = questionList.get(currentQuestion).option3
                    binding.opt4.text = questionList.get(currentQuestion).option4
                }
            }

        binding.catImg.setImageResource(image)
        binding.coin.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = wiythDrawal()
            bottomSheetDialog.show(this.supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        binding.Coin.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = wiythDrawal()
            bottomSheetDialog.show(this.supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }

        binding.opt1.setOnClickListener {
            nextQuestionAndScoreUpdate()
        }
        binding.opt2.setOnClickListener {
            nextQuestionAndScoreUpdate()
        }
        binding.opt3.setOnClickListener {
            nextQuestionAndScoreUpdate()
        }
        binding.opt4.setOnClickListener {
            nextQuestionAndScoreUpdate()
        }
    }

    private fun nextQuestionAndScoreUpdate() {
        currentQuestion++

        if(currentQuestion>questionList.size){

            Toast.makeText(this,"iTS Time for another Subject",Toast.LENGTH_SHORT).show()

        }

        else{

        binding.question.text = questionList.get(currentQuestion).question
        binding.opt1.text = questionList.get(currentQuestion).option1
        binding.opt2.text = questionList.get(currentQuestion).option2
        binding.opt3.text = questionList.get(currentQuestion).option3
        binding.opt4.text = questionList.get(currentQuestion).option4

        }
    }
}