package com.example.earningapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earningapp.R
import com.example.earningapp.adapter.HistoryAdapter
import com.example.earningapp.databinding.FragmentHistoryBinding
import com.example.earningapp.databinding.FragmentSpinBinding
import com.example.earningapp.modelClass.HistorymodelClass
import com.example.earningapp.modelClass.User
import com.example.earningapp.wiythDrawal
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HistoryFragment : Fragment() {
val binding by lazy {
    FragmentHistoryBinding.inflate(layoutInflater)
}

    private var ListHistory  = ArrayList<HistorymodelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ListHistory.add(HistorymodelClass("12.45","200"))
        ListHistory.add(HistorymodelClass("12.46","Try Again"))
        ListHistory.add(HistorymodelClass("12.47","100"))
        ListHistory.add(HistorymodelClass("12.47","500"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


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


        var adapter = HistoryAdapter(ListHistory)
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerView.adapter = adapter
        binding.historyRecyclerView.setHasFixedSize(true)
        // Inflate the layout for this fragment

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

    companion object {

    }
}