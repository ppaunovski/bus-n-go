import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-input-field',
  standalone: true,
  imports: [],
  templateUrl: './input-field.component.html',
  styleUrl: './input-field.component.css'
})
export class InputFieldComponent {
  @Input() name: string | undefined;
  @Input() type: string | undefined;
  @Input() placeholder: string | undefined;
  @Input() id: string | undefined;
  @Input() customClass: string | undefined;
  @Input() required: boolean | undefined;
  @Input() autocomplete: string | undefined;
  @Input() label: string | undefined;
  @Input() additionalInfo: string | undefined;
  @Output() onInputChange: EventEmitter<string | undefined> = new EventEmitter();

  handleInput(value: string) {
    this.onInputChange.emit(value)
  }
}
