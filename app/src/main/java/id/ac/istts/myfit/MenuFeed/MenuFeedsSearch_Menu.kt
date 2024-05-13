package id.ac.istts.myfit.MenuFeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.myfit.databinding.FragmentMenuFeedsSearchMenuBinding

class MenuFeedsSearch_Menu : Fragment() {

    private lateinit var binding: FragmentMenuFeedsSearchMenuBinding

    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var menuFeedSearchUserAdapter: MenuFeedSearchUserAdapter
    private lateinit var layoutManagerSearch: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuFeedsSearchMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}