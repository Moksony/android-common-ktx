package hu.moksony.commonExample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.moksony.commonExample.databinding.FragmentTestBinding
import hu.moksony.android_common.viewBinding

class TestFragment : Fragment() {

    val binding by viewBinding(FragmentTestBinding::inflate)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}