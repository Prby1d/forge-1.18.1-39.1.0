package net.prybld.attachments.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.prybld.attachments.block.entity.AttachmentApplierBlockEntity;

import javax.annotation.Nullable;

public class AttachmentApplierBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public AttachmentApplierBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.FALSE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (world.hasNeighborSignal(pos)) {
            world.setBlock(pos, state.setValue(LIT, Boolean.TRUE), 3);
        } else {
            world.setBlock(pos, state.setValue(LIT, Boolean.FALSE), 3);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!world.isClientSide()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AttachmentApplierBlockEntity) {
                NetworkHooks.openGui((ServerPlayer) player, (AttachmentApplierBlockEntity) blockEntity, pos);
            } else {
                throw new IllegalStateException("Our container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AttachmentApplierBlockEntity(pos, state);
    }

    // JSON model integration
    public static final String MODEL_JSON = """
    {
      "parent": "block/cube_all",
      "textures": {
        "0": "attachments:block/attachmentapplier/base",
        "1": "attachments:block/attachmentapplier/podium",
        "2": "attachments:block/attachmentapplier/arm",
        "3": "attachments:block/attachmentapplier/top",
        "particle": "attachments:block/attachmentapplier/base"
      },
      "elements": [
        {
          "name": "base",
          "from": [0, 0, 0],
          "to": [16, 4, 16],
          "faces": {
            "north": {"uv": [8, 0, 16, 2], "texture": "#0"},
            "east": {"uv": [8, 2, 16, 4], "texture": "#0"},
            "south": {"uv": [8, 4, 16, 6], "texture": "#0"},
            "west": {"uv": [8, 6, 16, 8], "texture": "#0"},
            "up": {"uv": [8, 8, 0, 0], "texture": "#0"},
            "down": {"uv": [8, 8, 0, 16], "texture": "#0"}
          }
        },
        {
          "name": "podium",
          "from": [6, 4, 6],
          "to": [10, 9, 10],
          "faces": {
            "north": {"uv": [0, 0, 4, 5], "texture": "#1"},
            "east": {"uv": [4, 0, 8, 5], "texture": "#1"},
            "south": {"uv": [0, 5, 4, 10], "texture": "#1"},
            "west": {"uv": [4, 5, 8, 10], "texture": "#1"},
            "up": {"uv": [12, 4, 8, 0], "texture": "#1"},
            "down": {"uv": [12, 4, 8, 8], "texture": "#1"}
          }
        },
        {
          "name": "south_arm",
          "from": [7.25, -3, 14],
          "to": [8.75, 3.5, 15.5],
          "rotation": {"angle": -22.5, "axis": "x", "origin": [0, 0, 0]},
          "faces": {
            "north": {"uv": [0, 0, 2, 7], "texture": "#2"},
            "east": {"uv": [2, 0, 4, 7], "texture": "#2"},
            "south": {"uv": [4, 0, 6, 7], "texture": "#2"},
            "west": {"uv": [6, 0, 8, 7], "texture": "#2"},
            "up": {"uv": [2, 9, 0, 7], "texture": "#2"},
            "down": {"uv": [4, 7, 2, 9], "texture": "#2"}
          }
        },
        {
          "name": "south_top",
          "from": [7, 8.5, 10.5],
          "to": [9, 9.5, 13.5],
          "faces": {
            "north": {"uv": [0, 4, 2, 5], "texture": "#3"},
            "east": {"uv": [0, 3, 3, 4], "texture": "#3"},
            "south": {"uv": [4, 0, 6, 1], "texture": "#3"},
            "west": {"uv": [3, 3, 6, 4], "texture": "#3"},
            "up": {"uv": [2, 3, 0, 0], "texture": "#3"},
            "down": {"uv": [4, 0, 2, 3], "texture": "#3"}
          }
        },
        {
          "name": "north_arm",
          "from": [7.25, 3, -0.7],
          "to": [8.75, 9.5, 0.8],
          "rotation": {"angle": 22.5, "axis": "x", "origin": [0, 0, 0]},
          "faces": {
            "north": {"uv": [0, 0, 2, 7], "texture": "#2"},
            "east": {"uv": [0, 0, 2, 7], "texture": "#2"},
            "south": {"uv": [0, 0, 2, 7], "texture": "#2"},
            "west": {"uv": [0, 0, 2, 7], "texture": "#2"},
            "up": {"uv": [0, 0, 2, 2], "texture": "#2"},
            "down": {"uv": [0, 0, 2, 2], "texture": "#2"}
          }
        },
        {
          "name": "north_top",
          "from": [7, 4, 1.5],
          "to": [9, 5, 4.5],
          "faces": {
            "north": {"uv": [0, 0, 2, 1], "texture": "#3"},
            "east": {"uv": [0, 0, 3, 1], "texture": "#3"},
            "south": {"uv": [0, 0, 2, 1], "texture": "#3"},
            "west": {"uv": [0, 0, 3, 1], "texture": "#3"},
            "up": {"uv": [0, 0, 2, 2], "texture": "#3"},
            "down": {"uv": [0, 0, 2, 2], "texture": "#3"}
          }
        }
      ]
    }
    """;
}
