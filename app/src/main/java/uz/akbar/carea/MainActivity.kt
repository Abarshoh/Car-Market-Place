package uz.akbar.carea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.akbar.carea.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, SplashFragment())
            .commit()



//        loadFragment(HomeFragment())
//        bottomNav = binding.navigationmenu as BottomNavigationView
//
//        bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.menu_home -> {
//                    loadFragment(HomeFragment())
//                    true
//                }
//                R.id.menu_profile -> {
//                    loadFragment(ProfileFragment())
//                    true
//                }
//                else -> {
//                    loadFragment(HomeFragment())
//                    true
//                }
//            }
//        }
//    }
//
//        private  fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragmentContainerView,fragment)
//        transaction.commit()
//    }
    }
}