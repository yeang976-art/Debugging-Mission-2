package org.example.expert.config;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.manager.repository.ManagerRepository;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 로컬 개발·조회 테스트용 초기 데이터.
 * SOUND VOLTEX I~VII 수록곡에서 임의로 고른 고난도 채보를 사용한다.
 * 이미 저장된 곡은 건너뛰므로 애플리케이션을 재시작해도 중복되지 않는다.
 */
@Component
@Profile("!prod")
@RequiredArgsConstructor
public class SoundVoltexInitData implements CommandLineRunner {

    private static final String SEED_EMAIL = "voltex@seed.local";
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        User seedUser = userRepository.findByEmail(SEED_EMAIL)
                .orElseGet(() -> userRepository.save(new User(
                        "SOUND VOLTEX",
                        SEED_EMAIL,
                        passwordEncoder.encode("soundvoltex18"),
                        UserRole.USER
                )));

        Set<String> existingTitles = todoRepository.findAll().stream()
                .filter(todo -> todo.getUser().getId().equals(seedUser.getId()))
                .map(Todo::getTitle)
                .collect(Collectors.toSet());

        List<Song> songs = List.of(
                // Lv. 18: SOUND VOLTEX I~VII에서 임의 선택 (50곡)
                song("croiX", "INF 18.6", "Clear"),
                song("neu BSP style", "XCD 18.5", "Cloudy"),
                song("Crack Traxxxx", "GRV 18.4", "Rainy"),
                song("Foolish Hero", "XCD 18.4", "Sunny"),
                song("有頂天ビバーチェ", "XCD 18.2", "Clear"),
                song("gigadelic (かめりあ's The TERA RMX)", "EXH 18.6", "Cloudy"),
                song("conflict", "XCD 18.5", "Rainy"),
                song("Garakuta Doll Play", "HVN 18.6", "Sunny"),
                song("XHAOS JUDGE", "EXH 18.8", "Clear"),
                song("End to end", "EXH 18.6", "Cloudy"),
                song("きたさいたま2000", "EXH 18.7", "Rainy"),
                song("Akzeriyyuth", "MXM 18.0", "Sunny"),
                song("怒槌", "MXM 18.7", "Clear"),
                song("Dreadnought", "MXM 18.6", "Cloudy"),
                song("Cepheus", "MXM 18.5", "Rainy"),
                song("FIN4LE ～終止線の彼方へ～", "EXH 18.7", "Sunny"),
                song("KIMIDORI Streak!!", "MXM 18.0", "Clear"),
                song("Gorgetech", "MXM 18.5", "Cloudy"),
                song("光射す澪のユズリハ", "MXM 18.5", "Rainy"),
                song("Sudden Visitor", "MXM 18.2", "Sunny"),
                song("Lazurite", "MXM 18.2", "Clear"),
                song("星の透る夏空に願う", "MXM 18.6", "Cloudy"),
                song("Jacob’s Elevator", "MXM 18.4", "Rainy"),
                song("Blessing Bouquet", "MXM 18.2", "Sunny"),
                song("無魎大数", "MXM 18.4", "Clear"),
                song("666", "EXH 18.5", "Cloudy"),
                song("夢幻泡影", "MXM 18.7", "Rainy"),
                song("Pure Ruby", "MXM 18.3", "Sunny"),
                song("随神", "MXM 18.5", "Clear"),
                song("Temporal Veil", "MXM 18.4", "Cloudy"),
                song("Like the Starlight", "MXM 18.2", "Rainy"),
                song("Enter The Rave", "MXM 18.5", "Sunny"),
                song("Red＋White＝Kawaii", "MXM 18.3", "Clear"),
                song("OZ", "MXM 18.6", "Cloudy"),
                song("ヴァルプルギスの夜", "MXM 18.5", "Rainy"),
                song("ON THE WORLD", "MXM 18.7", "Sunny"),
                song("ウイジン", "MXM 18.4", "Clear"),
                song("Lost Parliament", "MXM 18.3", "Cloudy"),
                song("～仔羊のナヴァラン・クリシェを添えて～", "MXM 18.5", "Rainy"),
                song("Don't you dare play GOD", "EXH 18.7", "Sunny"),
                song("XHRONOXAPSULΞ", "EXH 18.7", "Clear"),
                song("ИADIR", "MXM 18.4", "Cloudy"),
                song("Votum stellarum -forest #25 RMX-", "MXM 18.1", "Rainy"),
                song("Track Laundering", "MXM 18.6", "Sunny"),
                song("VIIIΧ", "MXM 18.5", "Clear"),
                song("Dot to Dot", "MXM 18.0", "Cloudy"),
                song("モノノケ狂想曲", "MXM 18.6", "Rainy"),
                song("GO!", "MXM 18.6", "Sunny"),
                song("Anti-Matter", "MXM 18.6", "Clear"),
                song("華麗なる！音戯探偵ひなビタ", "MXM 18.5", "Cloudy"),

                // Lv. 19: SOUND VOLTEX I~VII에서 임의 선택 (40곡)
                song("Ganymede kamome mix", "INF 19.3", "Rainy"),
                song("werewolf howls.", "XCD 19.2", "Sunny"),
                song("Joyeuse", "VVD 19.3", "Clear"),
                song("IX", "VVD 19.1", "Cloudy"),
                song("Blastix Riotz", "GRV 19.4", "Rainy"),
                song("FLügeL《Λrp:ΣggyØ》", "GRV 19.7", "Sunny"),
                song("ΑΩ-", "GRV 19.4", "Clear"),
                song("End to end", "XCD 19.2", "Cloudy"),
                song("きたさいたま2000", "HVN 19.1", "Rainy"),
                song("GODHEART", "MXM 19.2", "Sunny"),
                song("Staring at star", "MXM 19.1", "Clear"),
                song("KAC 2013 Empress Side", "MXM 19.7", "Cloudy"),
                song("TWO-TORIAL", "MXM 19.4", "Rainy"),
                song("†:OLPHEUX:†", "MXM 19.4", "Sunny"),
                song("Calamity Tempest", "MXM 19.4", "Clear"),
                song("THE凸GENERATOR", "MXM 19.8", "Cloudy"),
                song("Ghost Family", "MXM 19.5", "Rainy"),
                song("Guinevere～白き妖精～", "MXM 19.7", "Sunny"),
                song("Holy Trail", "MXM 19.3", "Clear"),
                song("Burst Λnd reBoost", "MXM 19.4", "Cloudy"),
                song("SAD1STIC Я04D", "MXM 19.3", "Rainy"),
                song("Electronic Sports Complex", "MXM 19.0", "Sunny"),
                song("onslaught -Retaliation of Bahamut-", "MXM 19.5", "Clear"),
                song("Plan 8", "MXM 19.3", "Cloudy"),
                song("CLAMARE", "MXM 19.1", "Rainy"),
                song("Ardenok", "MXM 19.4", "Sunny"),
                song("Allegro Saetta", "MXM 19.2", "Clear"),
                song("If Summer Ever Comes_", "MXM 19.8", "Cloudy"),
                song("Titanomachia", "MXM 19.3", "Rainy"),
                song("Fαtα∠ Ent∠mEnt", "MXM 19.4", "Sunny"),
                song("VOLAQUAS", "MXM 19.3", "Clear"),
                song("Who then no 灯", "MXM 19.7", "Cloudy"),
                song("Solitary Poison", "MXM 19.6", "Rainy"),
                song("XELENOPHOEBEA", "MXM 19.5", "Sunny"),
                song("Veins Resonance", "MXM 19.4", "Clear"),
                song("L2 -Reminiscence- (SDVX ver.)", "MXM 19.3", "Cloudy"),
                song("Circumzenith Arc", "MXM 19.7", "Rainy"),
                song("Zt!ri△", "MXM 19.5", "Sunny"),
                song("777 (Vocal ver.)", "MXM 19.7", "Clear"),
                song("Masterstroke", "MXM 19.8", "Cloudy"),

                // Lv. 20: SOUND VOLTEX III~VII에서 임의 선택 (10곡)
                song("Lachryma《Re:Queen'M》", "GRV 20.2", "Rainy"),
                song("iLLness LiLin", "MXM 20.2", "Sunny"),
                song("ΣmbryO", "MXM 20.2", "Clear"),
                song("666", "MXM 20.6", "Cloudy"),
                song("XHRONOXAPSULΞ", "MXM 20.6", "Rainy"),
                song("Don't you dare play GOD", "MXM 20.3", "Sunny"),
                song("APOCALYPSE RAY", "MXM 20.3", "Clear"),
                song("{ eXLIPXe }", "MXM 20.8", "Cloudy"),
                song("ΔLI∇E", "MXM 20.7", "Rainy"),
                song("KAC PERFECT ULTIMATE CHRONICLE", "MXM 20.8", "Sunny")
        );

        List<Todo> savedTodos = todoRepository.saveAll(songs.stream()
                .filter(song -> !existingTitles.contains(todoTitle(song)))
                .map(song -> new Todo(
                        todoTitle(song),
                        "SOUND VOLTEX · " + song.chart(),
                        song.weather(),
                        seedUser
                ))
                .toList());

        if (!savedTodos.isEmpty()) {
            managerRepository.saveAll(savedTodos.stream()
                    .flatMap(todo -> todo.getManagers().stream())
                    .toList());
        }
    }

    private String todoTitle(Song song) {
        return "[" + song.chart() + "] " + song.title();
    }

    private Song song(String title, String chart, String weather) {
        return new Song(title, chart, weather);
    }

    private record Song(String title, String chart, String weather) {}
}
