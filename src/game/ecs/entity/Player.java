package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InteractComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.AnimationState;
import utils.Settings.Movement;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;
import utils.Settings.Stats;
import utils.Settings.App;

/**
 * Class that represents a player.
 * @see AbstractEntity
 */
public class Player extends AbstractEntity
{
	/*----------------------------------------*/
	
	public int cameraX;
	public int cameraY;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of Player class.
	 * @param x X position
	 * @param y Y position
	 * @param animFrames Number of frames of the animation.
	 */
	public Player(int x, int y)
	{
		super();
		initialize(x, y);
		cameraX = App.SCREEN_W/2 - getComponent(SpriteComponent.class).getSpriteWidth()/2;
		cameraY = App.SCREEN_H/2 - getComponent(SpriteComponent.class).getSpriteHeight()/2;
	}
	
	/**
	 * Initialize entity components.
	 * @param x X position
	 * @param y Y position
	 * @param animFrames Number of frames of the animation.
	 */
	public void initialize(int x, int y)
	{
		// Create and add components
		KeyInputComponent inputs = new KeyInputComponent();
		addComponent(inputs);

		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);

		MovementComponent movement = new MovementComponent(Movement.PLAYER_SPEED, Movement.DOWN);
		addComponent(movement);

		SpriteComponent sprite = new SpriteComponent(ResFiles.PLAYER_SPRITESHEET, Sprites.PLAYER_SIZE, Sprites.PLAYER_SIZE);
		addComponent(sprite);

		AnimationComponent animation = new AnimationComponent(Sprites.ANIM_FRAMES, AnimationState.IDLE, Movement.NB_DIRECTIONS);
		addComponent(animation);

		ColliderComponent collider = new ColliderComponent(
			x,								// x
			y,								// y
			sprite.getSpriteWidth()/3,		// w
			sprite.getSpriteHeight()/4,		// h
			sprite.getSpriteWidth()/3,		// ox
			sprite.getSpriteHeight()/2,		// oy
			true							// isMoveable
		);
		addComponent(collider);
		
		DetectionComponent detection = new DetectionComponent(
			x,								// x
			y,								// y
			sprite.getSpriteWidth()*2,		// w
			sprite.getSpriteHeight()*2,		// h
			sprite.getSpriteWidth()/2,		// ox
			sprite.getSpriteHeight()/2		// oy
		);
		addComponent(detection);
		
		InteractComponent interact = new InteractComponent();
		addComponent(interact);
		
		InventoryComponent inventory = new InventoryComponent(7);
		addComponent(inventory);
		
		HealthComponent health = new HealthComponent(Stats.PLAYER_MAX_HEALTH);
		health.setCurrentHeath(health.getMaxHealth()-10);
		addComponent(health);
		
		AttackComponent attack = new AttackComponent(Stats.PLAYER_BASE_DAMAGE, Stats.PLAYER_ATTACK_COOLDOWN);
		attack.addAttackHitBox(
			(int)position.getX() + sprite.getSpriteWidth()/4,
			(int)position.getY() + sprite.getSpriteHeight()/4,
			sprite.getSpriteWidth()/3*2,
			sprite.getSpriteHeight()/2,
			sprite.getSpriteWidth()/6,
			sprite.getSpriteHeight()/4
		);
		addComponent(attack);
	}
	
	/*----------------------------------------*/
}
