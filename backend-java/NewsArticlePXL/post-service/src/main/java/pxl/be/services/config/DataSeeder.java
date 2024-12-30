package pxl.be.services.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pxl.be.services.domain.Post;
import pxl.be.services.domain.PostCategory;
import pxl.be.services.domain.PostStatus;
import pxl.be.services.repository.PostRepository;

import java.time.LocalDate;
import java.util.Random;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PostRepository postRepository;

    public DataSeeder(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (postRepository.count() == 0) {

            // PUBLISHED POSTS

            for (int i = 1; i <= 7; i++) {
                postRepository.save(Post.builder()
                        .title(retrieveTitleForArticle())
                        .content(retrieveARandomPostDescription())
                        .author(retrieveARandomAuthor())
                        .date(LocalDate.now())
                        .postStatus(PostStatus.PUBLISHED)
                        .category(PostCategory.SCIENCE)
                        .build());
            }

            for (int i = 1; i <= 6; i++) {
                postRepository.save(Post.builder()
                        .title(retrieveTitleForArticle())
                        .content(retrieveARandomPostDescription())
                        .author(retrieveARandomAuthor())
                        .date(LocalDate.now())
                        .postStatus(PostStatus.PUBLISHED)
                        .category(PostCategory.SPORTS)
                        .build());
            }

            for (int i = 1; i <= 9; i++) {
                postRepository.save(Post.builder()
                        .title(retrieveTitleForArticle())
                        .content(retrieveARandomPostDescription())
                        .author(retrieveARandomAuthor())
                        .date(LocalDate.now())
                        .postStatus(PostStatus.PUBLISHED)
                        .category(PostCategory.POLITICS)
                        .build());
            }

            for (int i = 1; i <= 4; i++) {
                postRepository.save(Post.builder()
                        .title(retrieveTitleForArticle())
                        .content(retrieveARandomPostDescription())
                        .author(retrieveARandomAuthor())
                        .date(LocalDate.now())
                        .postStatus(PostStatus.PUBLISHED)
                        .category(PostCategory.REGIONAL)
                        .build());
            }



            // UNPUBLISHED POSTS

            for (int i = 1; i <= 6; i++) {
                postRepository.save(Post.builder()
                        .title(retrieveTitleForArticle())
                        .content(retrieveARandomPostDescription())
                        .author(retrieveARandomAuthor())
                        .date(LocalDate.now())
                        .postStatus(PostStatus.APPROVED)
                        .category(PostCategory.REGIONAL)
                        .build());
            }

            for (int i = 1; i <= 2; i++) {
                postRepository.save(Post.builder()
                        .title(retrieveTitleForArticle())
                        .content(retrieveARandomPostDescription())
                        .author(retrieveARandomAuthor())
                        .date(LocalDate.now())
                        .postStatus(PostStatus.CONCEPT)
                        .category(PostCategory.SPORTS)
                        .build());
            }

            for (int i = 1; i <= 3; i++) {
                postRepository.save(Post.builder()
                        .title(retrieveTitleForArticle())
                        .content(retrieveARandomPostDescription())
                        .author(retrieveARandomAuthor())
                        .date(LocalDate.now())
                        .postStatus(PostStatus.WAITING_FOR_APPROVAL)
                        .category(PostCategory.POLITICS)
                        .build());
            }
            System.out.println("Seed data initialized");
        }
    }
    private String retrieveARandomPostDescription() {
        String[] listOfDescriptions = {
                "Electric vehicles (EVs) are no longer just a trend; they are becoming a cornerstone of the transportation industry. With governments worldwide pushing for carbon neutrality, EV manufacturers like Tesla, Rivian, and legacy automakers such as Ford and GM are ramping up production. As charging infrastructure expands and battery technology improves, EVs are becoming more affordable and practical for everyday consumers. However, challenges remain, such as the environmental impact of mining lithium for batteries and the need to upgrade power grids to handle increased electricity demand. Experts predict that by 2030, EVs could account for over 50% of global vehicle sales, marking a significant shift in how we think about mobility.",
                "Artificial intelligence (AI) is revolutionizing healthcare, from predictive diagnostics to personalized medicine. Machine learning algorithms analyze vast amounts of medical data, helping doctors identify patterns that humans might miss. For example, AI systems can detect early signs of diseases like cancer or Alzheimer's from imaging scans with unprecedented accuracy. However, this rapid advancement raises ethical questions about patient privacy, data security, and potential biases in AI algorithms. As healthcare becomes increasingly data-driven, policymakers and technologists must work together to ensure that AI is used responsibly, equitably, and transparently.",
                "As the planet warms, the impact on global food production is becoming evident. Prolonged droughts, unpredictable weather patterns, and rising sea levels are threatening crop yields worldwide. In regions like Sub-Saharan Africa and South Asia, where agriculture is a primary livelihood, millions face the risk of famine. Innovative solutions like vertical farming, genetically modified crops, and sustainable agricultural practices are being developed to combat these challenges. However, scaling these solutions globally requires significant investment and international cooperation. Without urgent action, climate change could push an additional 100 million people into extreme hunger by 2030.",
                "With NASA’s Perseverance rover and SpaceX’s ambitious plans for manned missions, Mars has become the focal point of modern space exploration. Scientists are studying the Martian surface for signs of ancient microbial life while engineering solutions to enable human colonization. Challenges such as radiation exposure, the thin atmosphere, and the psychological toll of long-duration space travel make this a formidable task. Yet, the potential benefits, from advancing science to ensuring humanity’s survival beyond Earth, drive these efforts forward. Experts believe that the first manned mission to Mars could happen within the next two decades, paving the way for a new era of interplanetary exploration.",
                "The COVID-19 pandemic has reshaped how we work, accelerating the adoption of remote work on a global scale. While many companies initially saw this as a temporary measure, the success of remote operations has led some to adopt hybrid or fully remote models permanently. Employees enjoy the flexibility, reduced commute times, and better work-life balance, while employers benefit from cost savings on office space. However, remote work also presents challenges, such as maintaining company culture, ensuring cybersecurity, and addressing employee isolation. As we navigate this new normal, the future of work seems poised to remain flexible, leveraging technology to bridge the gap between physical and virtual workplaces."
        };

        Random random = new Random();
        int randomIndex = random.nextInt(listOfDescriptions.length);

        return listOfDescriptions[randomIndex];
    }

    private String retrieveARandomAuthor() {
        String[] listOfAuthors = {
                "J.R.R Tolkien",
                "Stephan King",
                "J.K. Rowling",
                "William Shakespeare",
                "George R.R. Martin"
        };


        Random random = new Random();
        int randomIndex = random.nextInt(listOfAuthors.length);

        return listOfAuthors[randomIndex];
    }

    private String retrieveTitleForArticle() {
        String[] listOfTitles = {
                "The Rise of Electric Vehicles: How They’re Shaping the Future of Transportation",
                "AI in Healthcare: The Promise and the Ethical Dilemmas",
                "Climate Change and Global Food Security: A Looming Crisis",
                "Exploring Mars: The Next Frontier in Space Exploration",
                "The Evolution of Remote Work: Is the Office Obsolete?"
        };
        Random random = new Random();
        int randomIndex = random.nextInt(listOfTitles.length);

        return listOfTitles[randomIndex];
    }
}
