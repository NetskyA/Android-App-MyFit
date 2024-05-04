package id.ac.istts.myfit.MenuFeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.databinding.FragmentMenuFeedsSearchBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFeedsSearch.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFeedsSearch : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentMenuFeedsSearchBinding
    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var menuFeedSearchAdapter: MenuFeedSearchAdapter
    private lateinit var layoutManagerSearch: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuFeedsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var temp2:ArrayList<String> = arrayListOf("test","test2","test3","test4")

        recyclerViewSearch = binding.menuFeedsSearchRvSearch
        layoutManagerSearch = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        menuFeedSearchAdapter = MenuFeedSearchAdapter(temp2, onDetailClickListener = {
            // TODO: Open profile
        })
        recyclerViewSearch.adapter = menuFeedSearchAdapter
        recyclerViewSearch.layoutManager = layoutManagerSearch

        binding.svSearch.requestFocus()

    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFeedsSearch.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFeedsSearch().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}