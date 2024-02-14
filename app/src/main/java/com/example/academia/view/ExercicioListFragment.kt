package com.example.academia.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.academia.OnCarouselItemClickListener
import com.example.academia.R
import com.example.academia.adapters.CarouselAdapter
import com.example.academia.adapters.ExercicioListAdapter
import com.example.academia.databinding.FragmentExercicioListBinding
import com.example.academia.model.Entity
import com.example.academia.model.Treino
import com.example.academia.repository.EntityRepository
import com.example.academia.util.DateUtils
import com.example.academia.viewModel.DetalheExercicioViewModel
import com.example.academia.viewModel.ExercicioViewModel
import com.google.firebase.auth.FirebaseAuth

class ExercicioListFragment : Fragment(), OnCarouselItemClickListener {

    private lateinit var detalheViewModel: DetalheExercicioViewModel
    private lateinit var exercicioViewModel: ExercicioViewModel
    private lateinit var treinoViewModel: TreinoViewModel

    private lateinit var exercicioListAdapter: ExercicioListAdapter

    private var _binding: FragmentExercicioListBinding? = null
    private val binding get() = _binding!!

    private var nome: String? = null
    private var data: String? = null

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var carouselViewPager: CarouselViewPager
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExercicioListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Evita voltar para tela anterior ao clicar no botão voltar do aparelho
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            onBackPressed()
        }

        setHasOptionsMenu(true)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_ExercicioListFragment_to_TreinoListFragment)
        }

        detalheViewModel = ViewModelProvider(this).get(DetalheExercicioViewModel::class.java)
        exercicioViewModel = ViewModelProvider(this).get(ExercicioViewModel::class.java)
        treinoViewModel = ViewModelProvider(this).get(TreinoViewModel::class.java)

        carouselViewPager = view.findViewById(R.id.carousel)
        val entities: List<Entity> = EntityRepository().getEntities()
        carouselAdapter = CarouselAdapter(
            requireContext(),
            carouselViewPager,
            childFragmentManager,
            entities as ArrayList<Entity>,
            this
        )

        exercicioViewModel.exercicios.observe(viewLifecycleOwner, Observer { exercicios ->
            carouselAdapter.updateEntities(exercicios as ArrayList<Entity>?)
        })

        detalheViewModel.detalhesExercicios.observe(viewLifecycleOwner, Observer { detalhes ->
            // Lógica para tratar detalhes, se necessário
        })

        exercicioViewModel.carregarExercicios()
        detalheViewModel.carregarDetalhesExercicios()


        carouselViewPager.adapter = carouselAdapter
        carouselViewPager.addOnPageChangeListener(carouselAdapter)
        carouselViewPager.offscreenPageLimit = entities.size
        carouselViewPager.clipToPadding = false

        carouselViewPager.setScrollDurationFactor(1.0)

         //carouselViewPager.alpha = 0.0f

        //retirado deste link https://stackoverflow.com/questions/30129984/how-to-get-the-onwindowfocuschanged-on-fragment
//        view?.viewTreeObserver?.addOnWindowFocusChangeListener { hasFocus ->
//            carouselViewPager.startAnimationOnce(false, object : Animation.AnimationListener {
//                override fun onAnimationStart(animation: Animation) {
//                    // E exibimos o widget para vê-lo
//                    carouselViewPager.alpha = 1.0f
//
//                }
//
//                override fun onAnimationEnd(animation: Animation) {
//                    // Implementação do código após o término da animação, se necessário
//                }
//
//                override fun onAnimationRepeat(animation: Animation) {
//                    // Implementação do código em caso de repetição da animação, se necessário
//                }
//            })
//        }

        // Observa as mudanças na lista de exercícios
    }

    private val handler = Handler(Looper.getMainLooper())
    override fun onResume() {
        super.onResume()

        handler.post {
            // Seu código que precisa ser executado quando a fragment é retomada
            carouselViewPager.startAnimationOnce(false, object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // E exibimos o widget para vê-lo
                    carouselViewPager.alpha = 1.0f
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Implementação do código após o término da animação, se necessário
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Implementação do código em caso de repetição da animação, se necessário
                }
            })
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_exercicio_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sair -> {
                deslogarUsuario()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCarouselItemClick(entity: Entity?) {
        DialogConfigExercicio.showConfigurarExercicioDialog(
            requireContext(),
            "Configurar Treino",
            { nomeTreino, carga, tempo, quantidadeSeries, observacao ->
                val detalheExercicio =
                    detalheViewModel.detalhesExercicios.value?.find { it.nome == entity?.titleRes }

                nome = detalheExercicio?.nome.toString()
                data = detalheExercicio?.data.toString()

                val cargaformatada = "$carga Kg"
                val serieFormatada = "$quantidadeSeries Rp"

                val formattedDate = DateUtils.getCurrentFormattedDate()

                val novoTreino = Treino(
                    nomeTreino = nomeTreino,
                    nomeExercicio = nome!!,
                    data = formattedDate,
                    carga = cargaformatada,
                    tempoExercicio = tempo,
                    quantidadeSeries = serieFormatada,
                    observacao = observacao
                )

                treinoViewModel.adicionarExercicio(novoTreino)

                val bundle = Bundle().apply {
                    putSerializable("treino", novoTreino)
                }

                //Criar um Bundle e adicionar os detalhes do exercício
                if (findNavController().currentDestination?.id == R.id.FirstFragment) {
                    findNavController().navigate(
                        R.id.action_ExercicioListFragment_to_TreinoListFragment, bundle
                    )
                }
            },
            {}
        )
    }

    private fun deslogarUsuario() {
        AlertDialog.Builder(requireContext())
            .setTitle("Sair")
            .setMessage("Você realmente deseja sair?")
            .setPositiveButton("Sim") { _, _ ->
                firebaseAuth.signOut()
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            .setNegativeButton("Não", null)
            .show()
    }

    var doubleBackToExitPressedOnce = false

    fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            // Chama a função para fechar o aplicativo somente quando doubleBackToExitPressedOnce é true
            finishApplication()
        } else {
            doubleBackToExitPressedOnce = true
            Toast.makeText(requireContext(), "Pressione novamente para sair", Toast.LENGTH_SHORT).show()

            // Define um temporizador para redefinir doubleBackToExitPressedOnce após um período de 2 segundos
            Handler().postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)
        }
    }

    fun finishApplication() {
        // Finaliza a atividade principal e fecha o aplicativo
        requireActivity().finishAffinity()
    }
}
