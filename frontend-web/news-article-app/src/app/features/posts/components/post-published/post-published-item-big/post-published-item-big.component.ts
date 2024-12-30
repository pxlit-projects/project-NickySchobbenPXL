import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../../../../core/models/posts/Post";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-post-published-item-big',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './post-published-item-big.component.html',
  styleUrl: './post-published-item-big.component.css'
})
export class PostPublishedItemBigComponent implements OnInit{
  @Input() post!: Post;
  backgroundPicture = '';
  imageUrls = [
    'https://api.time.com/wp-content/uploads/2020/07/never-trumpers-2020-election-01.jpg?quality=85&amp;w=1201&amp;h=676&amp;crop=1',
    'https://api.time.com/wp-content/uploads/2020/07/president-trump-coronavirus-election.jpg?quality=85&amp;w=364&amp;h=204&amp;crop=1',
    'https://api.time.com/wp-content/uploads/2020/07/American-Flag.jpg?quality=85&amp;w=364&amp;h=204&amp;crop=1',
    'https://api.time.com/wp-content/uploads/2020/06/GettyImages-1222922545.jpg?quality=85&amp;w=364&amp;h=204&amp;crop=1',
    'https://i0.wp.com/learnwithwrl.com/wp-content/uploads/2022/02/Titanic_front_page.jpg?fit=500%2C362&ssl=1',
    'https://images.highfile.com/wp-content/uploads/2024/03/Classic-A4-Newspaper-Article-Template-Page-01.png?strip=all&lossy=1&quality=90&webp=90&sharp=1&ssl=1&fit=971,1244',
    'https://images.tijd.be/view?iid=Elvis:3bDMOGJK4RbAJrGz_RRqgw&context=ONLINE&ratio=16/9&width=640&u=1658911318000',
    'https://img.static-rmg.be/a/view/q75/w962/h503/f50.00,50.00/5436590/untitled-2-gif.gif',
    'https://img.nieuwsblad.be/FF7I2XTE-I-fPZK26wTu8INZkl8=/fit-in/640x427/https%3A%2F%2Fstatic.nieuwsblad.be%2FAssets%2FImages_Upload%2F2016%2F07%2F13%2F12fb3a64-48f5-11e6-be96-0002c4cecb93_web_scale_0.3974563_0.3974563__.jpg',
  ];

  ngOnInit(): void {
    this.backgroundPicture = this.getRandomImageUrl();
  }

  getRandomImageUrl(): string {
    const randomIndex = Math.floor(Math.random() * this.imageUrls.length);
    return this.imageUrls[randomIndex];
  }
}
