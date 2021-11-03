package com.example.meet.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meet.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FirstFragment : Fragment() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    lateinit var mDbRef: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        mAuth = FirebaseAuth.getInstance()






        setUpRecycler(view)

        return view
    }

    private fun setUpRecycler(view: View) {

        mDbRef = FirebaseDatabase.getInstance().getReference()


        userList = ArrayList()
        adapter = UserAdapter(activity as Context,userList)

        userRecyclerView = view.findViewById(R.id.userRecyclerView) as RecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(activity)
        userRecyclerView.adapter = adapter
        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(User::class.java)

                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                        userList.add(currentUser!!)
                    }


                }
                adapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }




}