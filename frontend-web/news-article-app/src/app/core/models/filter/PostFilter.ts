export class PostFilter {
  field: string;
  value: string;

  constructor(field: string, value: string) {
    this.field = field;
    this.value = value;
  }
}
