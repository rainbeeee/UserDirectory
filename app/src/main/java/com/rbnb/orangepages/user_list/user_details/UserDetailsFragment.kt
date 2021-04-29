package com.rbnb.orangepages.user_list.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rbnb.orangepages.R
import com.rbnb.orangepages.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private val user by lazy {
        safeArgs.user
    }

    private val safeArgs: UserDetailsFragmentArgs by navArgs()
    private val mapCallback = OnMapReadyCallback { googleMap ->
        val addressLatLng = LatLng(user.lat, user.long)
        googleMap.addMarker(MarkerOptions().position(addressLatLng).title(user.city))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(addressLatLng))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        binding.user = user
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapUserDetails) as SupportMapFragment?
        mapFragment?.getMapAsync(mapCallback)
    }
}