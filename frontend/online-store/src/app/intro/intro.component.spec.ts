import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { IntroComponent } from './intro.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        IntroComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(IntroComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'online-store'`, () => {
    const fixture = TestBed.createComponent(IntroComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('online-store');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(IntroComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('online-store app is running!');
  });
});
