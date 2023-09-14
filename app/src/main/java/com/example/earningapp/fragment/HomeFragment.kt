package com.example.earningapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.earningapp.R
import com.example.earningapp.adapter.categoryAdapter
import com.example.earningapp.databinding.FragmentHomeBinding
import com.example.earningapp.modelClass.User
import com.example.earningapp.modelClass.categoryModelClass
import com.example.earningapp.wiythDrawal
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

     private  val binding: FragmentHomeBinding by lazy {
         FragmentHomeBinding.inflate(layoutInflater)
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryList.add(categoryModelClass(R.drawable.scince1,"science"))
        categoryList.add(categoryModelClass(R.drawable.english1,"english"))
        categoryList.add(categoryModelClass(R.drawable.geography,"History"))
        categoryList.add(categoryModelClass(R.drawable.math,"MathMatic"))
        categoryList.add(categoryModelClass(R.drawable.mathmetic,"IQ"))
    }
 private var categoryList = ArrayList<categoryModelClass>()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.categoryRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        var adapter = categoryAdapter(categoryList,requireActivity())
        binding.categoryRecyclerView.adapter = adapter
        binding.categoryRecyclerView.setHasFixedSize(true)
    }
    companion object {

    }
}