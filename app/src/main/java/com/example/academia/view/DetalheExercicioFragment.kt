package com.example.academia.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.academia.R
import com.example.academia.adapters.TreinoListAdapter
import com.example.academia.databinding.FragmentDetalhesExercicioBinding
import com.example.academia.model.DetalheExercicio
import com.example.academia.model.Treino
import com.example.academia.util.Contador
import com.example.academia.util.formatarTempo
import com.example.academia.viewModel.DetalheExercicioViewModel
import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController


class DetalhesExercicioFragment() : Fragment() {

    private var _binding: FragmentDetalhesExercicioBinding? = null
    private lateinit var treinoViewModel: TreinoViewModel
    private lateinit var detalheViewModel: DetalheExercicioViewModel

    private lateinit var treino: Treino
    private lateinit var treinoListAdapter: TreinoListAdapter

    private val binding get() = _binding!!

    private lateinit var academiaTimer: Contador
    private val obs: String? = null

    private lateinit var circularProgressBar: ProgressBar
    private var progressTime: Int = 0 // ou

    private var mediaPlayer: MediaPlayer? = null
    private val musicResourceId = R.raw.the_carpente_novo

    private var isTimerRunning: Boolean = false

    private lateinit var counterMediaPlayer: MediaPlayer

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressedMethod()
        }

    }

    private lateinit var countDownTimer: CountDownTimer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentDetalhesExercicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        circularProgressBar = binding.circularProgressBar
        circularProgressBar.max = progressTime
        circularProgressBar.progress = progressTime

        counterMediaPlayer = MediaPlayer.create(requireContext(), R.raw.beep_02)

        binding.contadorTextView.visibility = View.INVISIBLE

//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            // Handle the back button event
//        }
//
//        callback.handleOnBackCancelled()
//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            showExitConfirmationDialog()
//        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        callback.run {  }

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        detalheViewModel = ViewModelProvider(this).get(DetalheExercicioViewModel::class.java)
        treinoViewModel = ViewModelProvider(requireActivity()).get(TreinoViewModel::class.java)


        val nomeExercicio = arguments?.getString("nomeExercicio")
        //val obs = arguments?.getString("obs")


        arguments?.let {
            treino = it.getSerializable("treino") as Treino
            //treinoListAdapter = it.getSerializable("adapter") as TreinoListAdapter

        }

       // binding.textObs.text = treino.observacao

        detalheViewModel.carregarDetalhesExercicios()


        //carregarDadosExercicio(nomeExercicio)
        detalheViewModel.detalhesExercicios.observe(viewLifecycleOwner, Observer { detalhes ->
            carregarDadosExercicio(nomeExercicio, detalhes)
        })

        binding.buttonSalvarTempo.setOnClickListener {

            finalizar()

//            Toast.makeText(
//                requireContext(),
//                "Treino finalizado!",
//                Toast.LENGTH_SHORT
//            ).show()
        }
    }

    private fun carregarDadosExercicio(
        nomeExercicio: String?,
        detalhesExercicios: List<DetalheExercicio>
    ) {
        // Encontrar o DetalheExercicio correspondente ao nome recebido
        val detalheExercicio = detalhesExercicios.find { it.nome == treino.nomeExercicio }

        val textTime = treino.tempoExercicio
        if (textTime != null) {
            val tempoInicialMillis = textTime.toLong() * 1000L
            progressTime = (tempoInicialMillis).toInt()
            circularProgressBar.max = progressTime.toInt()
            circularProgressBar.progress = progressTime.toInt()
            iniciarContador((progressTime.toLong() / 1000).toString()) // Inicie o contador com o tempo desejado em milissegundos
        }

        val tempoDaLista = textTime  // Substitua isso pelo tempo real da lista
         val tempoFormatado = tempoDaLista?.let { formatarTempo(it) }
        binding.textTempoRestante.text = tempoDaLista

        detalheExercicio?.let {
            // Preencher os campos com os dados do exercício
            binding.textNomeExercicio.text = it.nome
         //   binding.textDetalhesDescricao.text = it.descricao
            binding.textTempoRestante.text = tempoFormatado
         //   binding.textCarga.text = treino.carga
          //  binding.textQtdSerie.text = treino.quantidadeSeries
          //  binding.textObs.text = treino.observacao

            // Carregar a imagem usando Glide
            Glide.with(this)
                .load(it.imagemUrl) // Substitua com a URL ou recurso da imagem
                .placeholder(R.drawable.foto_academia) // Recurso de placeholder enquanto carrega
                .error(com.google.android.material.R.drawable.mtrl_ic_error) // Recurso de erro, se a carga falhar
                .into(binding.imageDetalhesExercicio)
        }

    }

    private fun iniciarContador(textTime: String) {
        circularProgressBar.progress = 0
        if (textTime.isNotBlank()) {
            try {
                val tempoInicialMillis = textTime.toLong() * 1000L  // Converter para milissegundos
                academiaTimer = Contador(
                    totalMillis = tempoInicialMillis,
                    intervalMillis = 1000,
                    onTick = { formattedTime ->
                        binding.textTempoRestante.text = formattedTime
                        // Atualize o ProgressBar conforme o tempo restante
                        circularProgressBar.progress = (tempoInicialMillis - academiaTimer.getTempoAtual()).toInt().coerceAtLeast(0) + 1000
                    },
                    onFinish = {
                        binding.textTempoRestante.text = "00:00"
                        Toast.makeText(requireContext(), "Treino finalizado!", Toast.LENGTH_SHORT)
                            .show()
                        // Aqui você pode reativar os botões se necessário
                        binding.buttonSalvarTempo.isEnabled = true
                        binding.btnPlay.isEnabled = true
                        binding.btnPause.isEnabled = true
                        binding.btnStop.isEnabled = true
                        circularProgressBar.progress = 0
                        stopMusic()

                        isTimerRunning = false
                    }
                )

                binding.btnPlay.setOnClickListener {
                    //academiaTimer.start()
                    // Desativar o botão Play
                    binding.btnPlay.isEnabled = false
                    // Reativar os botões Pause e Stop
                    binding.btnPause.isEnabled = true
                    binding.btnStop.isEnabled = true

                    startCounter()

                    startMusic()
                    isTimerRunning = true
                }

                binding.btnPause.setOnClickListener {
                    academiaTimer.pause()
                    // Desativar o botão Pause
                    binding.btnPause.isEnabled = false
                    // Reativar os botões Play e Stop
                    binding.btnPlay.isEnabled = true
                    binding.btnStop.isEnabled = true

                    isTimerRunning = false
                }

                val textTime = treino.tempoExercicio
                val tempoDaLista = textTime  // Substitua isso pelo tempo real da lista
                val tempoFormatado = tempoDaLista?.let { formatarTempo(it) }
                binding.btnStop.setOnClickListener {
                    academiaTimer.stop()
                    // Zerar os botões e reativar os botões Play e Pause
                    binding.buttonSalvarTempo.isEnabled = true
                    binding.textTempoRestante.text = tempoFormatado
                    binding.btnPlay.isEnabled = true
                    binding.btnPause.isEnabled = true
                    binding.btnStop.isEnabled = false

                    stopMusic()
                    circularProgressBar.progress = 0

                    isTimerRunning = false
                }

            } catch (e: NumberFormatException) {
                // Lidar com a conversão mal-sucedida, por exemplo, mostrar um log
                Log.e(
                    "DetalhesExercicioFragment",
                    "Erro ao converter textTime para Long: $textTime",
                    e
                )
            }
        } else {
            // Lidar com o caso em que textTime está vazio
            Log.e("DetalhesExercicioFragment", "textTime está vazio ou nulo")
        }
    }

    private fun startMusic() {
        mediaPlayer = MediaPlayer.create(requireContext(), musicResourceId)
        mediaPlayer?.start()
    }

    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun showExitConfirmationDialog() {

            AlertDialog.Builder(requireContext())
                .setTitle("Confirmação")
                .setMessage("Deseja realmente sair? Isso finalizará o treino.")
                .setPositiveButton("Sim") { _, _ ->
                    stopMusic()
                    academiaTimer.stop()
                    findNavController().navigate(R.id.action_SecondFragment_to_container_treino)
//                    findNavController().navigate(
//                        R.id.action_SecondFragment_to_FirstFragment
//                    )
                }
                .setNegativeButton("Cancelar", null)
                .show()
    }

    private  fun finalizar() {
        if (isTimerRunning) {
            // O contador está em execução, exibimos o diálogo de confirmação
            AlertDialog.Builder(requireContext())
                .setTitle("Confirmação")
                .setMessage("Deseja realmente sair? Isso finalizará o treino.")
                .setPositiveButton("Sim") { _, _ ->
                    stopMusic()
                    academiaTimer.stop()
                    binding.btnPlay.isEnabled = true
                    binding.btnStop.isEnabled = true
                    binding.btnPause.isEnabled = true
                    circularProgressBar.progress = 0

                    val intent = Intent(requireContext(), FinalizarTreino::class.java)
                    intent.putExtra("nomeTreino", treino.nomeTreino)
                    startActivity(intent)
                }
                .setNegativeButton("Cancelar", null)
                .show()
        } else {
            // O contador não está em execução, podemos prosseguir para a próxima tela
            val intent = Intent(requireContext(), FinalizarTreino::class.java)
            intent.putExtra("nomeTreino", treino.nomeTreino)
            startActivity(intent)
        }
    }

    private val handler = Handler(Looper.getMainLooper())

    private fun onBackPressedMethod() {
        if (isTimerRunning) {
                // Adiar a exibição da dialog para a thread principal
                handler.post {
                    showExitConfirmationDialog()
                }

        } else {
            // Se o contador não estiver em execução, prosseguir para a próxima tela
            findNavController().navigate(R.id.action_SecondFragment_to_container_treino)
        }
    }

    private fun startCounter() {
        binding.contadorTextView.visibility = View.VISIBLE
        if (!isTimerRunning) {
            // Inicialize o CountDownTimer
            countDownTimer = object : CountDownTimer(5000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // Lógica a ser executada a cada tick
                    val secondsLeft = (millisUntilFinished / 1000).toString()
                    binding.contadorTextView.text = secondsLeft
                    playBip()
                }

                override fun onFinish() {
                    // Lógica a ser executada quando o contador chegar a zero
                    binding.contadorTextView.text = "0"
                    playFinalBip()
                    isTimerRunning = false

                    binding.contadorTextView.visibility = View.INVISIBLE
                }
            }

            countDownTimer.start()
            isTimerRunning = true
        }
    }

    private fun playBip() {
        // Lógica para reproduzir o som beep_01
        counterMediaPlayer.reset()
        counterMediaPlayer.setDataSource(
            requireContext(),
            Uri.parse("android.resource://${requireContext().packageName}/${R.raw.beep_01}")
        )
        counterMediaPlayer.prepare()
        counterMediaPlayer.start()
    }

    private fun playFinalBip() {
        // Lógica para reproduzir o som beep_02
        counterMediaPlayer.reset()
        counterMediaPlayer.setDataSource(
            requireContext(),
            Uri.parse("android.resource://${requireContext().packageName}/${R.raw.beep_02}")
        )
        counterMediaPlayer.prepare()
        counterMediaPlayer.start()
        academiaTimer.start()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        stopMusic()
        counterMediaPlayer.release()
    }

}
