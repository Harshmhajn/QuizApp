package com.example.earningapp.fragment

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.earningapp.R
import com.example.earningapp.databinding.FragmentSpinBinding
import com.example.earningapp.modelClass.User
import com.example.earningapp.wiythDrawal
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import  java.util.Random
import kotlin.random.nextInt


class SpinFragment : Fragment() {

      private lateinit var binding: FragmentSpinBinding
      private lateinit var timer: CountDownTimer

      private  var itemTitles = arrayOf("100","Try Again","500","Try Again","200","Try Again")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding  = FragmentSpinBinding.inflate(inflater,container,false)

        Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid).addListenerForSingleValueEvent(
            object  : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    var  user = snapshot.getValue(User::class.java)

                    binding.spinYourName.text = user?.name



                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
        return binding.root
    }
private  fun showResult(itemTitle: String){
    Toast.makeText(requireContext(),itemTitle,Toast.LENGTH_SHORT).show()


    if(itemTitle != "Try Again") {
        var coinval = Integer.parseInt(binding.coin.text.toString())
        coinval =coinval + Integer.parseInt(itemTitle)
        binding.coin.text = coinval.toString()

    }
    binding.spin.isEnabled = true
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.coin.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = wiythDrawal()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        binding.Coin.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = wiythDrawal()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        binding.spin.setOnClickListener {
            var spinTimes = Integer.parseInt(binding.chance.text.toString())
            if(spinTimes > 0) {


                binding.spin.isEnabled = false
                val sppin = Random().nextInt(6)
                val degrees = 60f * sppin

                timer = object : CountDownTimer(7000, 50) {
                    var rotation = 0f
                    override fun onTick(millisUntilFinished: Long) {
                        rotation += 5f
                        if (rotation >= degrees) {
                            rotation = degrees
                            timer.cancel()
                            showResult(itemTitles[sppin])
                        }
                        binding.wheel.rotation = rotation
                    }

                    override fun onFinish() {}
                }.start()
                spinTimes = spinTimes - 1
                binding.chance.text = spinTimes.toString()

            }
            else{

            }
        }
    }

}