package id.ac.istts.myfit.MenuDaily

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import id.ac.istts.myfit.R
import id.ac.istts.myfit.databinding.FragmentMenuDailyBinding


class MenuDaily : Fragment() {

    private lateinit var binding: FragmentMenuDailyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var temp:ArrayList<String> = arrayListOf("test", "test2", "test3","test", "test2", "test3","test", "test2", "test3","test", "test2", "test3")

        val imageView: ImageView = requireView().findViewById(R.id.imageView7)
        val textWe: TextView = requireView().findViewById(R.id.tvTitleWe3)
        val clycontentmenutoday: ConstraintLayout = requireView().findViewById(R.id.lycontentmenutoday)
        val buttonprevios: TextView = requireView().findViewById(R.id.buttonprevios)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_right)
        imageView.startAnimation(fadeInAnimation)

        val fadeInAnimation1 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        clycontentmenutoday.startAnimation(fadeInAnimation1)

        val fadeInAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_left)
        textWe.startAnimation(fadeInAnimation2)

        /*val fadeInAnimation3 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        buttonnext.startAnimation(fadeInAnimation3)*/

        val fadeInAnimation4 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        buttonprevios.startAnimation(fadeInAnimation4)

        binding.lycontentmenutoday.setOnClickListener {
            findNavController().navigate(R.id.action_menuDaily_to_selectedDayMenus)
        }

    }
}